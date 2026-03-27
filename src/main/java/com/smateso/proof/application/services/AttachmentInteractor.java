/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IAttachmentInteractor;
import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.exceptions.AlreadyExistsException;
import com.smateso.proof.adapter.exceptions.InUseException;
import com.smateso.proof.adapter.exceptions.MismatchException;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.AttachmentRepository;
import edu.kit.iai.webis.proofmodels.dao.AttachmentDao;
import edu.kit.iai.webis.proofmodels.dto.Attachment;
import edu.kit.iai.webis.proofmodels.mapper.AttachmentMapper;
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
public class AttachmentInteractor implements IAttachmentInteractor {

    private static final String ENTITY_ATTACHMENT = "attachments";
    private final IMessageInteractor messageInteractor;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentMapper attachmentMapper;

    @Override
    public Page<Attachment> searchAttachments(@NonNull final Request request) {
        final Specification<AttachmentDao> specification = createSpecification(request);
        final var pageable = createPageable(request);
        final var result = this.attachmentRepository.findAll(specification, pageable);
        return result.map(this.attachmentMapper::convert);
    }

    @Override
    public List<Attachment> getAttachments() {
        final var result = this.attachmentRepository.findAll();
        return this.attachmentMapper.convert(result);
    }

    @Override
    public Attachment getAttachment(@NonNull final String id) {
        final var result = this.attachmentRepository.findById(id);
        if (result.isPresent()) return this.attachmentMapper.convert(result.get());
        else throw new NotFoundException("Attachment with id %s not found".formatted(id));
    }

    @Override
    public Attachment createAttachment(@NonNull final Attachment attachment, @Nullable final String sessionKey, @NonNull final String username) {
        if (Objects.nonNull(attachment.getId()) && !attachmentRepository.existsById(attachment.getId())) {
            final var result = this.attachmentRepository.save(this.attachmentMapper.convert(attachment));
            this.messageInteractor.sendUpdate(ENTITY_ATTACHMENT, requireNonNull(result.getId()), CREATED, sessionKey, username);
            return this.attachmentMapper.convert(result);
        } else {
            throw new AlreadyExistsException("Attachment with id %s already exists".formatted(attachment.getId()));
        }
    }

    @Override
    public Attachment updateAttachment(@NonNull final String id, @NonNull final Attachment update, @Nullable final String sessionKey, @NonNull final String username) {
        if (!Objects.equals(id, update.getId())) {
            throw new MismatchException("ID mismatch: Path ID (%s) does not match Request ID (%s)".formatted(id, update.getId()));
        }
        if (this.attachmentRepository.existsById(id)) {
            final var result = this.attachmentRepository.save(this.attachmentMapper.convert(update));
            this.messageInteractor.sendUpdate(ENTITY_ATTACHMENT, requireNonNull(result.getId()), UPDATED, sessionKey, username);
            return this.attachmentMapper.convert(result);
        } else {
            throw new NotFoundException("Attachment with id %s not found".formatted(id));
        }
    }

    @Override
    public void deleteAttachment(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username) {
        try {
            this.attachmentRepository.deleteById(id);
            this.messageInteractor.sendUpdate(ENTITY_ATTACHMENT, id, DELETED, sessionKey, username);
        } catch (final DataIntegrityViolationException e) {
            throw new InUseException("Attachment with id %s is still in use by another entity".formatted(id));
        }
    }

}
