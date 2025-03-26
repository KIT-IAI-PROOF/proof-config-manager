package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IAttachmentInteractor;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.AttachmentRepository;
import edu.kit.iai.webis.proofmodels.dao.AttachmentDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.List;

import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createPageable;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createSpecification;
import static com.smateso.proof.application.utils.LogMessages.ATTACHMENT_DAO_NOT_FOUND_MESSAGE;
import static com.smateso.proof.application.utils.PropertyCopyHelper.relationalCopyEntityProperties;

@Slf4j
@Builder
@AllArgsConstructor
public class AttachmentInteractor implements IAttachmentInteractor {

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
        return this.attachmentRepository.save(attachmentDao);
    }

    @Override
    public AttachmentDao updateAttachment(@NonNull final String id, @NonNull final AttachmentDao updatingAttachment, @NonNull final String sessionKey, @NonNull final String username) {
        final var existingAttachment = this.getAttachment(id);
        return this.attachmentRepository.save(relationalCopyEntityProperties(updatingAttachment, existingAttachment, true, List.of()));
    }

    @Override
    public void deleteAttachment(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        this.attachmentRepository.deleteById(id);
    }

}
