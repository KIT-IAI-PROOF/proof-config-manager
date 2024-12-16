package com.smateso.proof.application.interactors;

import com.smateso.proof.application.exceptions.NotFoundException;
import com.smateso.proof.application.model.Request;
import com.smateso.proof.application.repositories.WorkflowRepository;
import edu.kit.iai.webis.proofmodels.Workflow;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.smateso.proof.application.model.Action.*;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;

@CacheConfig(cacheNames = "workflowCache")
public class WorkflowInteractor implements IWorkflowInteractor {

    private final WorkflowRepository workflowRepository;
    private final MessageInteractor messageInteractor;

    public WorkflowInteractor(@NonNull final WorkflowRepository workflowRepository, @NonNull final MessageInteractor messageInteractor) {
        this.workflowRepository = workflowRepository;
        this.messageInteractor = messageInteractor;
    }

    @Override
    public Page<Workflow> searchWorkflows(@NonNull final Request request) {
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
            if (filter != null) {
                final var items = filter.getItems();
                items.forEach((final Request.FilterItem item) -> predicates.add(builder.equal(root.get(item.getField()), item.getValue())));
            }
            return builder.and(predicates.toArray(new Predicate[]{}));
        }, pageable);
    }

    @Override
    @Cacheable(value = "workflow", key = "#id", unless = "#result == null")
    public Workflow getWorkflow(@NonNull final String id) {
        final var result = this.workflowRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("Workflow with id %s not found".formatted(id));
        }
    }

    @Override
    @Caching(
            put = {@CachePut(cacheNames = "workflow", key = "#workflow.id", unless = "#workflow.id == null")},
            evict = {@CacheEvict(cacheNames = "workflows", allEntries = true)}
    )
    public Workflow createWorkflow(@NonNull final Workflow workflow, @NonNull final String sessionKey, @NonNull final String username) {
        final var result = this.workflowRepository.save(workflow);
        this.messageInteractor.workflowUpdate(result.getId(), CREATED, sessionKey, username);
        return result;
    }

    @Override
    @Caching(
            put = {@CachePut(cacheNames = "workflow", key = "#id")},
            evict = {@CacheEvict(cacheNames = "workflows", allEntries = true)}
    )
    public Workflow updateWorkflow(@NonNull final String id, @NonNull final Workflow workflow, @NonNull final String sessionKey, @NonNull final String username) {
        final var existingWorkflow = this.workflowRepository.findById(id).orElseThrow();
        workflow.setId(existingWorkflow.getId());
        final var result = this.workflowRepository.save(workflow);
        this.messageInteractor.workflowUpdate(result.getId(), UPDATED, sessionKey, username);
        return result;
    }

    @Override
    @Caching(
            evict = {@CacheEvict(cacheNames = "workflow", key = "#id"), @CacheEvict(cacheNames = "workflows", allEntries = true)}
    )
    public void deleteWorkflow(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        this.workflowRepository.deleteById(id);
        this.messageInteractor.workflowUpdate(id, DELETED, sessionKey, username);
    }

    @Override
    @Cacheable(value = "workflows")
    public List<Workflow> getWorkflows() {
        return this.workflowRepository.findAll();
    }

}
