package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IAttachmentController;
import com.smateso.proof.adapter.boundaries.services.IAttachmentInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.AttachmentDao;
import edu.kit.iai.webis.proofmodels.dto.AttachmentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.application.utils.PropertyCopyHelper.copyEntityProperties;
import static java.nio.file.Files.copy;
import static java.util.Objects.requireNonNull;

@Builder
@AllArgsConstructor
public class AttachmentController implements IAttachmentController {

    private final IAttachmentInteractor attachmentInteractor;
    private final Path fileStorageRoot;

    @Override
    public ResponseEntity<List<AttachmentDto>> searchAttachments(@NonNull final Request request) {
        final var attachments = this.attachmentInteractor.searchAttachments(request);
        final var result = attachments.stream().map((final var attachment) -> copyEntityProperties(attachment, new AttachmentDto(), false, List.of())).toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<AttachmentDto>> getAttachments() {
        final var attachments = this.attachmentInteractor.getAttachments();
        final var result = attachments.stream().map((final var attachment) -> copyEntityProperties(attachment, new AttachmentDto(), false, List.of())).toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<AttachmentDto> getAttachment(final String id) {
        final var attachment = this.attachmentInteractor.getAttachment(id);
        final var result = copyEntityProperties(attachment, new AttachmentDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<AttachmentDto> createAttachment(@NonNull final AttachmentDto update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var attachment = this.attachmentInteractor.createAttachment(copyEntityProperties(update, new AttachmentDao(), false, List.of()), sessionKey, username);
        final var result = copyEntityProperties(attachment, new AttachmentDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<AttachmentDto> updateAttachment(@NonNull final String attachmentId, @NonNull final AttachmentDto update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var attachment = this.attachmentInteractor.updateAttachment(attachmentId, copyEntityProperties(update, new AttachmentDao(), false, List.of()), sessionKey, username);
        final var result = copyEntityProperties(attachment, new AttachmentDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteAttachment(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.attachmentInteractor.deleteAttachment(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> uploadFile(@NonNull final MultipartFile file) {
        String filename = file.getOriginalFilename();
        try {
            final var filePath = this.fileStorageRoot.resolve(requireNonNull(filename));
            copy(file.getInputStream(), filePath);
            return ResponseEntity.ok().build();
        } catch (final FileAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A file of that name already exists.");
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload the file");
        }
    }

}
