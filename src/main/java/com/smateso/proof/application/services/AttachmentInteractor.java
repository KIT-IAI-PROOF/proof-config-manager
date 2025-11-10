package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IAttachmentInteractor;
import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.exceptions.InUseException;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.AttachmentRepository;
import edu.kit.iai.webis.proofmodels.dao.AttachmentDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.List;

import static com.smateso.proof.adapter.model.Action.*;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createPageable;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createSpecification;
import static com.smateso.proof.application.utils.LogMessages.ATTACHMENT_DAO_IN_USE_MESSAGE;
import static com.smateso.proof.application.utils.LogMessages.ATTACHMENT_DAO_NOT_FOUND_MESSAGE;
import static com.smateso.proof.application.utils.PropertyCopyHelper.deepCopyEntityProperties;
import static java.util.Objects.requireNonNull;

@Slf4j
@Builder
@AllArgsConstructor
public class AttachmentInteractor implements IAttachmentInteractor {

    private static final List<String> COMPLEX_FIELDS = List.of();
    private static final String ENTITY_ATTACHMENT = "attachments";
    private final IMessageInteractor messageInteractor;
    private final AttachmentRepository attachmentRepository;

    @Override
    public Page<AttachmentDao> searchAttachments(@NonNull final Request request) {
        final Specification<AttachmentDao> specification = createSpecification(request);
        final var pageable = createPageable(request);
        return this.attachmentRepository.findAll(specification, pageable);
    }

    @Override
    public List<AttachmentDao> getAttachments() {
        return this.attachmentRepository.findAll();
    }

    @Override
    public AttachmentDao getAttachment(@NonNull final String id) {
        final var result = this.attachmentRepository.findById(id);
        if (result.isPresent()) return result.get();
        else throw new NotFoundException(ATTACHMENT_DAO_NOT_FOUND_MESSAGE.formatted(id));
    }

    @Override
    public AttachmentDao createAttachment(@NonNull final AttachmentDao attachmentDao, @NonNull final String sessionKey, @NonNull final String username) {
        final var result = this.attachmentRepository.save(attachmentDao);
        this.messageInteractor.sendUpdate(ENTITY_ATTACHMENT, requireNonNull(result.getId()), CREATED, sessionKey, username);
        return result;
    }

    @Override
    public AttachmentDao updateAttachment(@NonNull final String id, @NonNull final AttachmentDao partial, @NonNull final String sessionKey, @NonNull final String username) {
        final var optionalAttachment = this.attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            final var attachment = optionalAttachment.get();
            deepCopyEntityProperties(partial, attachment, true, List.of(), COMPLEX_FIELDS);
            final var result = this.attachmentRepository.save(attachment);
            this.messageInteractor.sendUpdate(ENTITY_ATTACHMENT, requireNonNull(result.getId()), UPDATED, sessionKey, username);
            return result;
        } else {
            final var result = this.attachmentRepository.save(partial);
            this.messageInteractor.sendUpdate(ENTITY_ATTACHMENT, requireNonNull(result.getId()), UPDATED, sessionKey, username);
            return result;
        }
    }

    @Override
    public void deleteAttachment(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        try {
            this.attachmentRepository.deleteById(id);
            this.messageInteractor.sendUpdate(ENTITY_ATTACHMENT, id, DELETED, sessionKey, username);
        } catch (final DataIntegrityViolationException e) {
            throw new InUseException(ATTACHMENT_DAO_IN_USE_MESSAGE.formatted(id));
        }
    }

}
