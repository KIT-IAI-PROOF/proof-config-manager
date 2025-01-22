package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.boundaries.services.ITemplateInteractor;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.TemplateRepository;
import edu.kit.iai.webis.proofmodels.Template;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.smateso.proof.adapter.model.Action.CREATED;
import static com.smateso.proof.adapter.model.Action.DELETED;
import static com.smateso.proof.adapter.utils.NullAwareUtil.getNullPropertyNames;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;

public class TemplateInteractor implements ITemplateInteractor {

    private final TemplateRepository templateRepository;
    private final com.smateso.proof.adapter.boundaries.services.IMessageInteractor IMessageInteractor;

    public TemplateInteractor(@NonNull final TemplateRepository templateRepository, @NonNull final IMessageInteractor IMessageInteractor) {
        this.templateRepository = templateRepository;
        this.IMessageInteractor = IMessageInteractor;
    }

    @Override
    public Page<Template> searchTemplates(@NonNull final Request request) {
        final Pageable pageable;
        if (request.getPagination() != null) {
            if (request.getSort() != null) {
                final var sort = by(request.getSort().stream().map((final Request.Sort requestSort) -> new Sort.Order(Sort.Direction.fromString(requestSort.getSort()), requestSort.getField())).toList());
                pageable = of(request.getPagination().getPage(), request.getPagination().getPageSize(), sort);
            } else {
                pageable = of(request.getPagination().getPage(), request.getPagination().getPageSize());
            }
        } else {
            pageable = Pageable.unpaged();
        }
        return this.templateRepository.findAll((final Root<Template> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            final var filter = request.getFilter();
            if (filter != null) {
                final var items = filter.getItems();
                items.forEach((final Request.FilterItem item) -> predicates.add(builder.equal(root.get(item.getField()), item.getValue())));
            }
            return builder.and(predicates.toArray(new Predicate[]{}));
        }, pageable);
    }

    @Override
    public Template getTemplate(@NonNull final String id) {
        final var result = this.templateRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("Template with id %s not found".formatted(id));
        }
    }

    @Override
    public Template createTemplate(@NonNull final Template template, @NonNull final String sessionKey, @NonNull final String username) {
        final var result = this.templateRepository.save(template);
        this.IMessageInteractor.templateUpdate(result.getId(), CREATED, sessionKey, username);
        return result;
    }

    @Override
    public Template updateTemplate(@NonNull final String id, @NonNull final Template template, @NonNull final String sessionKey, @NonNull final String username) {
        final var existingTemplate = this.getTemplate(id);
        copyProperties(template, existingTemplate, getNullPropertyNames(template));
        existingTemplate.setTargetHandles(existingTemplate.getTargetHandles());
        existingTemplate.setSourceHandles(existingTemplate.getSourceHandles());
        return this.templateRepository.save(existingTemplate);
    }

    @Override
    public void deleteTemplate(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        this.templateRepository.deleteById(id);
        this.IMessageInteractor.templateUpdate(id, DELETED, sessionKey, username);
    }

    @Override
    public List<Template> getTemplates() {
        return this.templateRepository.findAll();
    }

}
