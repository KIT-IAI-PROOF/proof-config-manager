package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.boundaries.services.IWorkflowInteractor;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.BlockRepository;
import com.smateso.proof.adapter.repositories.EdgeRepository;
import com.smateso.proof.adapter.repositories.ExecutionRepository;
import com.smateso.proof.adapter.repositories.WorkflowRepository;
import edu.kit.iai.webis.proofmodels.dao.WorkflowDao;
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

import static com.smateso.proof.adapter.model.Action.*;
import static com.smateso.proof.adapter.utils.NullAwareUtil.getNullPropertyNames;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.getPath;
import static java.util.Objects.requireNonNull;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;

public class WorkflowInteractor implements IWorkflowInteractor {

    private final IMessageInteractor messageInteractor;
    private final WorkflowRepository workflowRepository;
    private final EdgeRepository edgeRepository;
    private final BlockRepository blockRepository;
    private final ExecutionRepository executionRepository;

    public WorkflowInteractor(@NonNull final WorkflowRepository workflowRepository, @NonNull final BlockRepository blockRepository, @NonNull final EdgeRepository edgeRepository, @NonNull final ExecutionRepository executionRepository, @NonNull final IMessageInteractor messageInteractor) {
        this.workflowRepository = workflowRepository;
        this.blockRepository = blockRepository;
        this.edgeRepository = edgeRepository;
        this.executionRepository = executionRepository;
        this.messageInteractor = messageInteractor;
    }

    @Override
    public Page<WorkflowDao> searchWorkflows(@NonNull final Request request) {
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
        return this.workflowRepository.findAll((final Root<WorkflowDao> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            final var filter = request.getFilter();
            if (filter != null) {
                final var items = filter.getItems();
                items.forEach((final Request.FilterItem item) -> predicates.add(builder.equal(getPath(root, item.getField()), item.getValue())));
            }
            return builder.and(predicates.toArray(new Predicate[]{}));
        }, pageable);
    }

    @Override
    public WorkflowDao getWorkflow(@NonNull final String id) {
        final var result = this.workflowRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("WorkflowDao with id %s not found".formatted(id));
        }
    }

    @Override
    public WorkflowDao createWorkflow(@NonNull final WorkflowDao workflowDao, @NonNull final String sessionKey, @NonNull final String username) {
        final var result = this.workflowRepository.save(workflowDao);
        this.messageInteractor.workflowUpdate(requireNonNull(result.getId()), CREATED, sessionKey, username);
        return result;
    }

    @Override
    public WorkflowDao updateWorkflow(@NonNull final String id, @NonNull final WorkflowDao workflowDao, @NonNull final String sessionKey, @NonNull final String username) {
        final var existingWorkflow = this.getWorkflow(id);
        copyProperties(workflowDao, existingWorkflow, getNullPropertyNames(workflowDao));
        existingWorkflow.setBlocks(existingWorkflow.getBlocks());
        existingWorkflow.setEdges(existingWorkflow.getEdges());
        existingWorkflow.setExecutions(existingWorkflow.getExecutions());
        final var result = this.workflowRepository.save(workflowDao);
        this.messageInteractor.workflowUpdate(requireNonNull(result.getId()), UPDATED, sessionKey, username);
        return result;
    }

    @Override
    public void deleteWorkflow(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        this.workflowRepository.deleteById(id);
        this.messageInteractor.workflowUpdate(id, DELETED, sessionKey, username);
    }

    @Override
    public List<WorkflowDao> getWorkflows() {
        return this.workflowRepository.findAll();
    }

}
