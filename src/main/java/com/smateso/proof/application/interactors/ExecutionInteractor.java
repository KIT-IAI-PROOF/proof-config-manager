package com.smateso.proof.application.interactors;

import com.smateso.proof.application.exceptions.NotFoundException;
import com.smateso.proof.application.model.Request;
import com.smateso.proof.application.repositories.ExecutionRepository;
import edu.kit.iai.webis.proofmodels.Execution;
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

@CacheConfig(cacheNames = "executionCache")
public class ExecutionInteractor implements IExecutionInteractor {

    private final ExecutionRepository executionRepository;
    private final MessageInteractor messageInteractor;

    public ExecutionInteractor(@NonNull final ExecutionRepository executionRepository, @NonNull final MessageInteractor messageInteractor) {
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
    @Cacheable(value = "execution", key = "#id", unless = "#result == null")
    public Execution getExecution(@NonNull final String id) {
        final var result = this.executionRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("Execution with id %s not found".formatted(id));
        }
    }

    @Override
    @Caching(
            put = {@CachePut(cacheNames = "execution", key = "#execution.id", unless = "#execution.id == null")},
            evict = {@CacheEvict(cacheNames = "executions", allEntries = true)}
    )
    public Execution createExecution(@NonNull final Execution execution, @NonNull final String sessionKey, @NonNull final String username) {
        final var result = this.executionRepository.save(execution);
        this.messageInteractor.executionUpdate(result.getId(), CREATED, sessionKey, username);
        return result;
    }

    @Override
    @Caching(
            put = {@CachePut(cacheNames = "execution", key = "#id")},
            evict = {@CacheEvict(cacheNames = "executions", allEntries = true)}
    )
    public Execution updateExecution(@NonNull final String id, @NonNull final Execution execution, @NonNull final String sessionKey, @NonNull final String username) {
        final var existingExecution = this.executionRepository.findById(id).orElseThrow();
        execution.setId(existingExecution.getId());
        final var result = this.executionRepository.save(execution);
        this.messageInteractor.executionUpdate(result.getId(), UPDATED, sessionKey, username);
        return result;
    }

    @Override
    @Caching(
            evict = {@CacheEvict(cacheNames = "execution", key = "#id"), @CacheEvict(cacheNames = "executions", allEntries = true)}
    )
    public void deleteExecution(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        this.executionRepository.deleteById(id);
        this.messageInteractor.executionUpdate(id, DELETED, sessionKey, username);
    }

    @Override
    @Cacheable(value = "executions")
    public List<Execution> getExecutions() {
        return this.executionRepository.findAll();
    }

}
