package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IAttachmentController;
import com.smateso.proof.adapter.boundaries.services.IAttachmentInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.AttachmentDao;
import edu.kit.iai.webis.proofmodels.dto.Attachment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static java.nio.file.Files.copy;
import static java.util.Objects.requireNonNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Builder
@AllArgsConstructor
public class AttachmentController implements IAttachmentController {

    private final IAttachmentInteractor attachmentInteractor;
    private final Path fileStorageRoot;

    @NonNull
    private static Attachment attachLinks(@NonNull final Attachment attachment) {
        final var selfLink = linkTo(AttachmentController.class).slash(attachment.getId()).withSelfRel();
        attachment.add(selfLink);
        return attachment;
    }

    @Override
    public ResponseEntity<List<Attachment>> searchAttachments(@NonNull final Request request) {
        final var attachments = this.attachmentInteractor.searchAttachments(request);
        final var results = attachments
                .stream()
                .map(AttachmentDao::convert)
                .map(AttachmentController::attachLinks)
                .toList();
        return ResponseEntity.ok(results);
    }

    @Override
    public ResponseEntity<List<Attachment>> getAttachments() {
        final var attachments = this.attachmentInteractor.getAttachments();
        final var results = attachments
                .stream()
                .map(AttachmentDao::convert)
                .map(AttachmentController::attachLinks)
                .toList();
        return ResponseEntity.ok(results);
    }

    @Override
    public ResponseEntity<List<Attachment>> exportAttachments() {
        return this.getAttachments();
    }

    @Override
    public ResponseEntity<Attachment> getAttachment(final String id) {
        final var attachment = this.attachmentInteractor.getAttachment(id).convert();
        return ResponseEntity.ok(attachment);
    }

    @Override
    public ResponseEntity<Attachment> createAttachment(@NonNull final Attachment attachment, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var result = this.attachmentInteractor.createAttachment(attachment.convert(), sessionKey, username).convert();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Attachment> updateAttachment(@NonNull final String attachmentId, @NonNull final Attachment attachment, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var result = this.attachmentInteractor.updateAttachment(attachmentId, attachment.convert(), sessionKey, username).convert();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteAttachment(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.attachmentInteractor.deleteAttachment(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<String> uploadFile(@NonNull final MultipartFile file) {
        final var filename = file.getOriginalFilename();
        try {
            final var filePath = this.fileStorageRoot.resolve(requireNonNull(filename));
            final var inputStream = file.getInputStream();
            copy(inputStream, filePath.normalize().toAbsolutePath());
            return ResponseEntity.ok(filePath.normalize().toAbsolutePath().toString());
        } catch (final FileAlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body("A file of that name already exists.");
        } catch (final Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Could not upload the file");
        }
    }

}
