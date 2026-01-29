package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IAttachmentController;
import com.smateso.proof.adapter.boundaries.services.IAttachmentInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.Attachment;
import edu.kit.iai.webis.proofmodels.paging.AttachmentPagingModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.smateso.proof.adapter.config.audit.AuditorProvider.getUsername;
import static java.nio.file.Files.copy;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpStatus.CONFLICT;

@Builder
@AllArgsConstructor
public class AttachmentController implements IAttachmentController {

    private final IAttachmentInteractor attachmentInteractor;
    private final Path fileStorageRoot;


    @Override
    public ResponseEntity<AttachmentPagingModel> searchAttachments(@NonNull final Request request) {
        final var attachments = this.attachmentInteractor.searchAttachments(request);
        final var result = AttachmentPagingModel
                .builder()
                .rowCount(attachments.getTotalElements())
                .results(attachments.toList())
                .build();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Attachment>> getAttachments() {
        final var attachments = this.attachmentInteractor.getAttachments();
        return ResponseEntity.ok(attachments);
    }

    @Override
    public ResponseEntity<List<Attachment>> exportAttachments() {
        return this.getAttachments();
    }

    @Override
    public ResponseEntity<Attachment> getAttachment(final String id) {
        final var attachment = this.attachmentInteractor.getAttachment(id);
        return ResponseEntity.ok(attachment);
    }

    @Override
    public ResponseEntity<Attachment> createAttachment(@NonNull final Attachment attachment, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        final var result = this.attachmentInteractor.createAttachment(attachment, sessionKey, username);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Attachment> updateAttachment(@NonNull final String attachmentId, @NonNull final Attachment attachment, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        final var result = this.attachmentInteractor.updateAttachment(attachmentId, attachment, sessionKey, username);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteAttachment(@NonNull final String id, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        this.attachmentInteractor.deleteAttachment(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<String> uploadFile(@NonNull final String id, @NonNull final MultipartFile file) {
        final var filename = file.getOriginalFilename();
        try {
            final var filePath = this.fileStorageRoot.resolve(id).resolve(requireNonNull(filename));
            final var inputStream = file.getInputStream();
            Files.createDirectories(this.fileStorageRoot);
            copy(inputStream, filePath.normalize().toAbsolutePath(), REPLACE_EXISTING);
            return ResponseEntity.ok(filePath.normalize().toAbsolutePath().toString());
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
            return ResponseEntity.internalServerError().build();
        }
    }

}
