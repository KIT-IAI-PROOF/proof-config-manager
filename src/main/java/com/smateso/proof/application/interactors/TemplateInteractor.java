package com.smateso.proof.application.interactors;

import com.smateso.proof.application.model.Request;
import com.smateso.proof.application.repositories.TemplateRepository;
import edu.kit.iai.webis.proofmodels.Template;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;

public class TemplateInteractor implements ITemplateInteractor {

    private final TemplateRepository templateRepository;

    public TemplateInteractor(final TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public Page<Template> getTemplates(final Request request) {
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
            final var items = filter.getItems();
            items.forEach((final Request.FilterItem item) -> predicates.add(builder.equal(root.get(item.getField()), item.getValue())));
            return builder.and(predicates.toArray(new Predicate[]{}));
        }, pageable);
    }

    @Override
    public Template getTemplate(final String id) {
        return this.templateRepository.findById(id).orElseThrow();
    }

    @Override
    public Template createTemplate(final Template template) {
        return this.templateRepository.save(template);
    }

    @Override
    public Template updateTemplate(String uuid, Template template) {
        final var existingTemplate = this.templateRepository.findById(uuid).orElseThrow();
        template.setId(existingTemplate.getId());
        return this.templateRepository.save(template);
    }

    @Override
    public void deleteTemplate(final String uuid) {
        this.templateRepository.deleteById(uuid);
    }

}
