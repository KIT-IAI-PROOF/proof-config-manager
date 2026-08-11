/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IFileController;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Objects.requireNonNullElse;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CONFLICT;

@Builder
@AllArgsConstructor
public class FileController implements IFileController {

    private final Path fileStorageRoot;

    @Override
    public ResponseEntity<String> uploadFile(@NonNull final MultipartFile file, final String type) {
        final var filename = file.getOriginalFilename();
        try {
            final Path filePath;
            switch (type) {
                case "userdata": {
                    filePath = this.fileStorageRoot.resolve("userdata").resolve(requireNonNullElse(filename, "file"));
                    break;
                }
                default: {
                    throw new RuntimeException("Invalid type for file storage");
                }
            }
            final var inputStream = file.getInputStream();
            Files.createDirectories(this.fileStorageRoot);
            copy(inputStream, filePath.normalize().toAbsolutePath(), REPLACE_EXISTING);
            return ResponseEntity.ok(this.fileStorageRoot.resolve("userdata").relativize(filePath).toString());
        } catch (final FileAlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body("A file of that name already exists.");
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<?> downloadFile(final String path) {
        try {
            final var filePath = this.fileStorageRoot.resolve(path).normalize();
            final Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                String mimeType = Files.probeContentType(filePath);
                MediaType mediaType = mimeType != null ? MediaType.parseMediaType(mimeType) : MediaType.APPLICATION_OCTET_STREAM;
                return ResponseEntity.ok()
                        .contentType(mediaType)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<List<String>> listFiles(final String type) {
        try {
            final Path directoryPath;
            switch (type) {
                case "userdata": {
                    directoryPath = this.fileStorageRoot.resolve("userdata");
                    break;
                }
                default: {
                    throw new RuntimeException("Invalid type for file storage");
                }
            }

            if (!Files.exists(directoryPath)) {
                return ResponseEntity.ok(List.of());
            }

            try (final var stream = Files.list(directoryPath)) {
                List<String> fileList = stream
                        .filter(file -> !Files.isDirectory(file))
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .collect(toList());
                return ResponseEntity.ok(fileList);
            }
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }


}
