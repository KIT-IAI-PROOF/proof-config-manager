package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.boundaries.services.ITemplateInteractor;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.TemplateRepository;
import edu.kit.iai.webis.proofmodels.dao.TemplateDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.List;

import static com.smateso.proof.adapter.model.Action.*;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createPageable;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createSpecification;
import static com.smateso.proof.application.utils.LogMessages.TEMPLATE_DAO_NOT_FOUND_MESSAGE;
import static com.smateso.proof.application.utils.PropertyCopyHelper.TemplatePropertyCopyHelper.copyNonNullTemplateProperties;
import static java.util.Objects.requireNonNull;

@Slf4j
@Builder
@AllArgsConstructor
public class TemplateInteractor implements ITemplateInteractor {

    private final IMessageInteractor messageInteractor;
    private final TemplateRepository templateRepository;

    @Override
    public Page<TemplateDao> searchTemplates(@NonNull final Request request) {
        final Specification<TemplateDao> specification = createSpecification(request);
        final var pageable = createPageable(request);
        return this.templateRepository.findAll(specification, pageable);
    }

    @Override
    public List<TemplateDao> getTemplates() {
        return this.templateRepository.findAll();
    }

    @Override
    public TemplateDao getTemplate(@NonNull final String id) {
        final var result = this.templateRepository.findById(id);
        if (result.isPresent()) return result.get();
        else throw new NotFoundException(TEMPLATE_DAO_NOT_FOUND_MESSAGE.formatted(id));
    }

    @Override
    public TemplateDao createTemplate(@NonNull final TemplateDao templateDao, @NonNull final String sessionKey, @NonNull final String username) {
        final var result = this.templateRepository.save(templateDao);
        this.messageInteractor.templateUpdate(requireNonNull(result.getId()), CREATED, sessionKey, username);
        return result;
    }

    @Override
    public TemplateDao updateTemplate(@NonNull final String id, @NonNull final TemplateDao updatingTemplate, @NonNull final String sessionKey, @NonNull final String username) {
        final var existingTemplate = this.getTemplate(id);
        final var result = this.templateRepository.save(copyNonNullTemplateProperties(updatingTemplate, existingTemplate));
        this.messageInteractor.templateUpdate(requireNonNull(result.getId()), UPDATED, sessionKey, username);
        return result;
    }

    @Override
    public void deleteTemplate(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        this.templateRepository.deleteById(id);
        this.messageInteractor.templateUpdate(id, DELETED, sessionKey, username);
    }

}
