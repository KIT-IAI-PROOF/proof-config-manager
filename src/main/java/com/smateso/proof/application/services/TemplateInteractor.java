package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.boundaries.services.ITemplateInteractor;
import com.smateso.proof.adapter.exceptions.AlreadyExistsException;
import com.smateso.proof.adapter.exceptions.InUseException;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.TemplateRepository;
import edu.kit.iai.webis.proofmodels.dao.TemplateDao;
import edu.kit.iai.webis.proofmodels.dto.Template;
import edu.kit.iai.webis.proofmodels.mapper.TemplateMapper;
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
import static com.smateso.proof.application.utils.LogMessages.*;
import static java.util.Objects.requireNonNull;

@Slf4j
@AllArgsConstructor
public class TemplateInteractor implements ITemplateInteractor {

    private static final String ENTITY_TEMPLATE = "templates";
    private final IMessageInteractor messageInteractor;
    private final TemplateRepository templateRepository;
    private final TemplateMapper templateMapper;

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
        else throw new NotFoundException(TEMPLATE_DAO_NOT_FOUND_MESSAGE.formatted(id));
    }

    @Override
    public Template createTemplate(@NonNull final Template template, @Nullable final String sessionKey, @NonNull final String username) {
        if (Objects.nonNull(template.getId()) && !templateRepository.existsById(template.getId())) {
            final var result = this.templateRepository.save(this.templateMapper.convert(template));
            this.messageInteractor.sendUpdate(ENTITY_TEMPLATE, requireNonNull(result.getId()), CREATED, sessionKey, username);
            return this.templateMapper.convert(result);
        } else {
            throw new AlreadyExistsException(TEMPLATE_DAO_EXISTS.formatted(template.getId()));
        }
    }

    @Override
    public Template updateTemplate(@NonNull final String id, @NonNull final Template partial, @Nullable final String sessionKey, @NonNull final String username) {
        final var optionalTemplate = this.templateRepository.findById(id);
        if (optionalTemplate.isPresent()) {
            final var template = optionalTemplate.get();
            this.templateMapper.patch(partial, template);
            final var result = this.templateRepository.save(template);
            this.messageInteractor.sendUpdate(ENTITY_TEMPLATE, requireNonNull(result.getId()), UPDATED, sessionKey, username);
            return this.templateMapper.convert(result);
        } else {
            throw new NotFoundException(TEMPLATE_DAO_NOT_FOUND_MESSAGE.formatted(id));
        }
    }

    @Override
    public void deleteTemplate(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username) {
        try {
            this.templateRepository.deleteById(id);
            this.messageInteractor.sendUpdate(ENTITY_TEMPLATE, id, DELETED, sessionKey, username);
        } catch (final DataIntegrityViolationException e) {
            throw new InUseException(TEMPLATE_DAO_IN_USE_MESSAGE.formatted(id));
        }
    }

}
