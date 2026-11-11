/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.boundaries.services.ITemplateInteractor;
import com.smateso.proof.adapter.exceptions.AlreadyExistsException;
import com.smateso.proof.adapter.exceptions.InUseException;
import com.smateso.proof.adapter.exceptions.MismatchException;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.BlockRepository;
import com.smateso.proof.adapter.repositories.TemplateRepository;
import com.smateso.proof.adapter.repositories.WorkflowRepository;
import edu.kit.iai.webis.proofmodels.dao.BlockDao;
import edu.kit.iai.webis.proofmodels.dao.TemplateDao;
import edu.kit.iai.webis.proofmodels.dto.Template;
import edu.kit.iai.webis.proofmodels.dto.Workflow;
import edu.kit.iai.webis.proofmodels.mapper.TemplateMapper;
import edu.kit.iai.webis.proofmodels.mapper.WorkflowMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;

import static com.smateso.proof.adapter.model.Action.*;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createPageable;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createSpecification;
import static java.util.Objects.requireNonNull;

@Slf4j
@AllArgsConstructor
public class TemplateInteractor implements ITemplateInteractor {

    private static final String ENTITY_TEMPLATE = "templates";
    private final IMessageInteractor messageInteractor;
    private final TemplateRepository templateRepository;
    private final WorkflowRepository workflowRepository;
    private final BlockRepository blockRepository;
    private final TemplateMapper templateMapper;
    private final WorkflowMapper workflowMapper;

    @Override
    public Page<Template> searchTemplates(@NonNull final Request request) {
        final Specification<TemplateDao> specification = createSpecification(request);
        final var pageable = createPageable(request);
        final var result = this.templateRepository.findAll(specification, pageable);
        return result.map(this.templateMapper::convert);
    }

    @Override
    public List<Template> getTemplates() {
        final var result = this.templateRepository.findAll();
        return this.templateMapper.convert(result);
    }

    @Override
    public Template getTemplate(@NonNull final String id) {
        final var result = this.templateRepository.findById(id);
        if (result.isPresent()) return this.templateMapper.convert(result.get());
        else throw new NotFoundException("Template with id %s not found".formatted(id));
    }

    @Override
    public Template createTemplate(@NonNull final Template template, @Nullable final String sessionKey, @NonNull final String username) {
        try {
            if (Objects.nonNull(template.getId()) && !templateRepository.existsById(template.getId())) {
                final var result = this.templateRepository.save(this.templateMapper.convert(template));
                this.messageInteractor.sendUpdate(ENTITY_TEMPLATE, requireNonNull(result.getId()), CREATED, sessionKey, username);
                return this.templateMapper.convert(result);
            } else {
                throw new AlreadyExistsException("Template with id %s already exists".formatted(template.getId()));
            }
        } catch (final DataIntegrityViolationException e) {
            throw new AlreadyExistsException(e);
        }
    }

    @Override
    public Template updateTemplate(@NonNull final String id, @NonNull final Template update, @Nullable final String sessionKey, @NonNull final String username) {
        try {
            if (!Objects.equals(id, update.getId())) {
                throw new MismatchException("ID mismatch: Path ID (%s) does not match Request ID (%s)".formatted(id, update.getId()));
            }
            if (this.templateRepository.existsById(id)) {
                final var result = this.templateRepository.save(this.templateMapper.convert(update));
                this.messageInteractor.sendUpdate(ENTITY_TEMPLATE, requireNonNull(result.getId()), UPDATED, sessionKey, username);
                return this.templateMapper.convert(result);
            } else {
                throw new NotFoundException("Template with id %s not found".formatted(id));
            }
        } catch (final DataIntegrityViolationException e) {
            throw new AlreadyExistsException(e);
        }
    }

    @Override
    public void deleteTemplate(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username) {
        try {
            if (!this.blockRepository.existsByTemplateId(id)) {
                this.templateRepository.deleteById(id);
                this.messageInteractor.sendUpdate(ENTITY_TEMPLATE, id, DELETED, sessionKey, username);
            } else {
                throw new InUseException("Template with id %s is still in use by a block".formatted(id));
            }
        } catch (final DataIntegrityViolationException e) {
            throw new InUseException("Template with id %s is still in use by another entity".formatted(id));
        }
    }

    @Override
    public List<Workflow> getWorkflowsForTemplate(@NonNull final String id) {
        final var blocks = this.blockRepository.findByTemplateId(id);
        final var workflows = this.workflowRepository.findDistinctByBlocks_IdIn(blocks.stream().map(BlockDao::getId).toList());
        return this.workflowMapper.convert(workflows);
    }

}
