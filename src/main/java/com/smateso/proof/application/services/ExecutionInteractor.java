/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.application.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smateso.proof.adapter.boundaries.services.IExecutionInteractor;
import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.exceptions.AlreadyExistsException;
import com.smateso.proof.adapter.exceptions.InUseException;
import com.smateso.proof.adapter.exceptions.MismatchException;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.ExecutionRepository;
import com.smateso.proof.adapter.repositories.TemplateRepository;
import edu.kit.iai.webis.proofmodels.dao.BlockDao;
import edu.kit.iai.webis.proofmodels.dao.ExecutionDao;
import edu.kit.iai.webis.proofmodels.dao.TemplateDao;
import edu.kit.iai.webis.proofmodels.dto.Execution;
import edu.kit.iai.webis.proofmodels.mapper.ExecutionMapper;
import edu.kit.iai.webis.proofmodels.mapper.TemplateMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static com.smateso.proof.adapter.model.Action.*;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createPageable;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createSpecification;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@Slf4j
@AllArgsConstructor
public class ExecutionInteractor implements IExecutionInteractor {

    private static final String ENTITY_EXECUTION = "executions";
    private final IMessageInteractor messageInteractor;
    private final ExecutionRepository executionRepository;
    private final TemplateRepository templateRepository;
    private final ExecutionMapper executionMapper;
    private final TemplateMapper templateMapper;
    private final Path fileStorageRoot;
    private final ObjectMapper mapper;

    @Override
    public Page<Execution> searchExecutions(@NonNull final Request request) {
        final Specification<ExecutionDao> specification = createSpecification(request);
        final var pageable = createPageable(request);
        final var result = this.executionRepository.findAll(specification, pageable);
        return result.map(this.executionMapper::convert);
    }

    @Override
    public List<Execution> getExecutions() {
        final var result = this.executionRepository.findAll();
        return this.executionMapper.convert(result);
    }

    @Override
    public Execution getExecution(@NonNull final String id) {
        final var result = this.executionRepository.findById(id);
        if (result.isPresent()) return this.executionMapper.convert(result.get());
        else throw new NotFoundException("Execution with id %s not found".formatted(id));
    }

    @Override
    public Execution createExecution(@NonNull final Execution execution, @Nullable final String sessionKey, @NonNull final String username) {
        if (nonNull(execution.getId()) && !executionRepository.existsById(execution.getId())) {
            final var result = this.executionRepository.save(this.executionMapper.convert(execution));
            this.messageInteractor.sendUpdate(ENTITY_EXECUTION, requireNonNull(result.getId()), CREATED, sessionKey, username);
            return this.executionMapper.convert(result);
        } else {
            throw new AlreadyExistsException("Execution with id %s already exists".formatted(execution.getId()));
        }
    }

    @Override
    public Execution updateExecution(@NonNull final String id, @NonNull final Execution update, @Nullable final String sessionKey, @NonNull final String username) {
        if (!Objects.equals(id, update.getId())) {
            throw new MismatchException("ID mismatch: Path ID (%s) does not match Request ID (%s)".formatted(id, update.getId()));
        }
        if (this.executionRepository.existsById(id)) {
            final var result = this.executionRepository.save(this.executionMapper.convert(update));
            this.messageInteractor.sendUpdate(ENTITY_EXECUTION, requireNonNull(result.getId()), UPDATED, sessionKey, username);
            return this.executionMapper.convert(result);
        } else {
            throw new NotFoundException("Execution with id %s not found".formatted(id));
        }
    }

    @Override
    public void deleteExecution(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username) {
        try {
            final var optionalExecution = this.executionRepository.findById(id);
            if (optionalExecution.isPresent()) {
                final var execution = optionalExecution.get();
                this.executionRepository.delete(execution);
                if (nonNull(execution.getWorkflow()) && nonNull(execution.getId())) {
                    if (nonNull(execution.getWorkflow().getBlocks())) {
                        this.messageInteractor.deleteBlockLoggingQueues(execution.getWorkflow().getBlocks().stream().map(BlockDao::getId).toList(), execution.getId());
                    }
                }
                this.messageInteractor.deleteExecutionLoggingQueue(id);
                this.messageInteractor.sendUpdate(ENTITY_EXECUTION, id, DELETED, sessionKey, username);
            }
        } catch (final DataIntegrityViolationException e) {
            throw new InUseException("Execution with id %s is still in use by another entity".formatted(id));
        }
    }

    @Override
    public Resource exportExecution(@NonNull final String id) {
        final var optionalExecution = this.executionRepository.findById(id);
        if (optionalExecution.isPresent()) {
            final var execution = optionalExecution.get();
            final var templates = this.templateRepository.findAll();
            final Set<String> addedEntries = new HashSet<>();
            try (final var byteArrayOutputStream = new ByteArrayOutputStream(); final var zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {

                zipOutputStream.putNextEntry(new ZipEntry("execution.json"));
                zipOutputStream.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(this.executionMapper.convert(execution)));
                zipOutputStream.closeEntry();
                addedEntries.add("execution.json");

                zipOutputStream.putNextEntry(new ZipEntry("templates.json"));
                zipOutputStream.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(this.templateMapper.convert(templates)));
                zipOutputStream.closeEntry();
                addedEntries.add("templates.json");

                if (nonNull(execution.getWorkflow()) && nonNull(execution.getWorkflow().getBlocks())) {
                    for (var block : execution.getWorkflow().getBlocks()) {
                        if (nonNull(block.getProgram()) && nonNull(block.getProgram().getAttachments())) {
                            for (var attachment : block.getProgram().getAttachments()) {
                                final var filePath = this.fileStorageRoot.resolve(requireNonNull(attachment.getPath()));
                                final String entryName = "files/" + attachment.getPath();
                                if (Files.exists(filePath) && !addedEntries.contains(entryName)) {
                                    zipOutputStream.putNextEntry(new ZipEntry(entryName));
                                    Files.copy(filePath, zipOutputStream);
                                    zipOutputStream.closeEntry();
                                    addedEntries.add(entryName);
                                }
                            }
                        }
                    }
                }

                zipOutputStream.finish();
                return new ByteArrayResource(byteArrayOutputStream.toByteArray());

            } catch (final IOException e) {
                log.error("Failed to create export ZIP for execution {}", id, e);
                throw new RuntimeException("Export failed", e);
            }
        } else {
            throw new NotFoundException("Execution with id %s not found".formatted(id));
        }
    }

    @Override
    public void importExecution(@NonNull final MultipartFile file, @Nullable final String sessionKey, @NonNull final String username) {

        ExecutionDao execution = null;
        List<TemplateDao> templates = null;

        try (ZipInputStream zis = new ZipInputStream(file.getInputStream())) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("execution.json")) {
                    execution = mapper.readValue(zis.readAllBytes(), ExecutionDao.class);
                } else if (entry.getName().equals("templates.json")) {
                    templates = mapper.readValue(zis.readAllBytes(), new TypeReference<List<TemplateDao>>() {
                    });
                } else if (entry.getName().startsWith("files/")) {
                    String fileName = entry.getName().substring(6);
                    Path targetPath = fileStorageRoot.resolve(fileName);
                    Files.createDirectories(targetPath.getParent());
                    Files.copy(zis, targetPath, REPLACE_EXISTING);
                }
                zis.closeEntry();
            }

            if (nonNull(execution)) this.executionRepository.save(execution);
            if (nonNull(templates)) this.templateRepository.saveAll(templates);

        } catch (final IOException e) {
            log.error("Failed to import execution", e);
            throw new RuntimeException("Import failed", e);
        }
    }

}
