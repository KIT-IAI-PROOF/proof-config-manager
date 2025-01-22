package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IExecutionInteractor;
import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.ExecutionRepository;
import edu.kit.iai.webis.proofmodels.Execution;
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
import static java.util.Objects.requireNonNull;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;

public class ExecutionInteractor implements IExecutionInteractor {

    private final ExecutionRepository executionRepository;
    private final IMessageInteractor messageInteractor;

    public ExecutionInteractor(@NonNull final ExecutionRepository executionRepository, @NonNull final IMessageInteractor messageInteractor) {
        this.executionRepository = executionRepository;
        this.messageInteractor = messageInteractor;
    }

    @Override
    public Page<Execution> searchExecutions(@NonNull final Request request) {
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
            if (filter != null) {
                final var items = filter.getItems();
                items.forEach((final Request.FilterItem item) -> predicates.add(builder.equal(root.get(item.getField()), item.getValue())));
            }
            return builder.and(predicates.toArray(new Predicate[]{}));
        }, pageable);
    }

    @Override
    public Execution getExecution(@NonNull final String id) {
        final var result = this.executionRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("Execution with id %s not found".formatted(id));
        }
    }

    @Override
    public Execution createExecution(@NonNull final Execution execution, @NonNull final String sessionKey, @NonNull final String username) {
        final var result = this.executionRepository.save(execution);
        this.messageInteractor.executionUpdate(result.getId(), CREATED, sessionKey, username);
        return result;
    }

    @Override
    public Execution updateExecution(@NonNull final String id, @NonNull final Execution execution, @NonNull final String sessionKey, @NonNull final String username) {
        final var existingExecution = this.getExecution(id);
        copyProperties(execution, existingExecution, getNullPropertyNames(existingExecution));
        existingExecution.setWorkflow(existingExecution.getWorkflow());
        final var result = this.executionRepository.save(existingExecution);
        this.messageInteractor.blockUpdate(requireNonNull(result.getId()), UPDATED, sessionKey, username);
        return result;
    }

    @Override
    public void deleteExecution(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        this.executionRepository.deleteById(id);
        this.messageInteractor.executionUpdate(id, DELETED, sessionKey, username);
    }

    @Override
    public List<Execution> getExecutions() {
        return this.executionRepository.findAll();
    }

}
