package com.smateso.proof.application.interactors;

import com.smateso.proof.application.model.Request;
import com.smateso.proof.application.repositories.WorkflowRepository;
import edu.kit.iai.webis.proofmodels.Workflow;
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

public class WorkflowInteractor implements IWorkflowInteractor {

    private final WorkflowRepository workflowRepository;

    public WorkflowInteractor(final WorkflowRepository workflowRepository) {
        this.workflowRepository = workflowRepository;
    }

    @Override
    public Page<Workflow> getWorkflows(final Request request) {
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
        return this.workflowRepository.findAll((final Root<Workflow> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            final var filter = request.getFilter();
            final var items = filter.getItems();
            items.forEach((final Request.FilterItem item) -> predicates.add(builder.equal(root.get(item.getField()), item.getValue())));
            return builder.and(predicates.toArray(new Predicate[]{}));
        }, pageable);
    }

    @Override
    public Workflow getWorkflow(final String id) {
        return this.workflowRepository.findById(id).orElseThrow();
    }

    @Override
    public Workflow createWorkflow(final Workflow workflow) {
        return this.workflowRepository.save(workflow);
    }

    @Override
    public Workflow updateWorkflow(String uuid, Workflow workflow) {
        final var existingWorkflow = this.workflowRepository.findById(uuid).orElseThrow();
        workflow.setId(existingWorkflow.getId());
        return this.workflowRepository.save(workflow);
    }

    @Override
    public void deleteWorkflow(final String uuid) {
        this.workflowRepository.deleteById(uuid);
    }

}
