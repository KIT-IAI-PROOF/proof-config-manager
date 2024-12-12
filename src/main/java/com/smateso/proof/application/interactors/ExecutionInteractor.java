package com.smateso.proof.application.interactors;

import com.smateso.proof.application.model.Request;
import com.smateso.proof.application.repositories.ExecutionRepository;
import edu.kit.iai.webis.proofmodels.Execution;
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

public class ExecutionInteractor implements IExecutionInteractor {

    private final ExecutionRepository executionRepository;

    public ExecutionInteractor(final ExecutionRepository executionRepository) {
        this.executionRepository = executionRepository;
    }

    @Override
    public Page<Execution> getExecutions(final Request request) {
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
        return this.executionRepository.findAll((final Root<Execution> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            final var filter = request.getFilter();
            final var items = filter.getItems();
            items.forEach((final Request.FilterItem item) -> predicates.add(builder.equal(root.get(item.getField()), item.getValue())));
            return builder.and(predicates.toArray(new Predicate[]{}));
        }, pageable);
    }

    @Override
    public Execution getExecution(final String id) {
        return this.executionRepository.findById(id).orElseThrow();
    }

    @Override
    public Execution createExecution(final Execution execution) {
        return this.executionRepository.save(execution);
    }

    @Override
    public Execution updateExecution(String uuid, Execution execution) {
        final var existingExecution = this.executionRepository.findById(uuid).orElseThrow();
        execution.setId(existingExecution.getId());
        return this.executionRepository.save(execution);
    }

    @Override
    public void deleteExecution(final String uuid) {
        this.executionRepository.deleteById(uuid);
    }

}
