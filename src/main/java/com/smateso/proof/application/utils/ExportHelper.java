/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.application.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kit.iai.webis.proofmodels.dao.ProgramDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

/**
 * Shared logic for building the export ZIP archives used by the Template, Workflow and Execution interactors.
 */
@Slf4j
public class ExportHelper {

    private ExportHelper() {
    }

    /**
     * Builds an export ZIP resource containing the primary entity as {@code <entityName>.json}, an optional
     * {@code templates.json} entry, and any attachment files referenced by the given programs.
     */
    public static Resource createExportZip(@NonNull final ObjectMapper mapper,
                                            @NonNull final Path fileStorageRoot,
                                            @NonNull final String entityName,
                                            @NonNull final String id,
                                            @NonNull final Object primaryEntity,
                                            @Nullable final Object templatesEntity,
                                            @NonNull final List<ProgramDao> programs) {
        final Set<String> addedEntries = new HashSet<>();
        try (final var byteArrayOutputStream = new ByteArrayOutputStream(); final var zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {

            writeJsonEntry(zipOutputStream, mapper, entityName + ".json", primaryEntity, addedEntries);

            if (nonNull(templatesEntity)) {
                writeJsonEntry(zipOutputStream, mapper, "templates.json", templatesEntity, addedEntries);
            }

            for (final var program : programs) {
                if (nonNull(program) && nonNull(program.getAttachments())) {
                    for (final var attachment : program.getAttachments()) {
                        final var filePath = fileStorageRoot.resolve("attachments").resolve(requireNonNull(attachment.getPath()));
                        log.debug("Processing attachment with path {}", filePath);
                        final String entryName = "files/" + attachment.getPath();
                        if (Files.exists(filePath) && !addedEntries.contains(entryName)) {
                            log.debug("Added entry {} to zip file", entryName);
                            zipOutputStream.putNextEntry(new ZipEntry(entryName));
                            Files.copy(filePath, zipOutputStream);
                            zipOutputStream.closeEntry();
                            addedEntries.add(entryName);
                        }
                    }
                }
            }

            zipOutputStream.finish();
            return new ByteArrayResource(byteArrayOutputStream.toByteArray());

        } catch (final IOException e) {
            log.error("Failed to create export ZIP for {} {}", entityName, id, e);
            throw new RuntimeException("Export failed", e);
        }
    }

    private static void writeJsonEntry(@NonNull final ZipOutputStream zipOutputStream,
                                        @NonNull final ObjectMapper mapper,
                                        @NonNull final String entryName,
                                        @NonNull final Object value,
                                        @NonNull final Set<String> addedEntries) throws IOException {
        zipOutputStream.putNextEntry(new ZipEntry(entryName));
        zipOutputStream.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(value));
        zipOutputStream.closeEntry();
        addedEntries.add(entryName);
    }

}
