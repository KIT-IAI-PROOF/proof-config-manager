package com.smateso.proof.adapter.repositories;

import edu.kit.iai.webis.proofmodels.Execution;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@CacheConfig(cacheNames = "executions")
public interface ExecutionRepository extends JpaRepository<Execution, String>, JpaSpecificationExecutor<Execution> {

    @NonNull
    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "executions", allEntries = true, beforeInvocation = true)
            },
            put = {
                    @CachePut(cacheNames = "executions", key = "#execution.id", unless = "#execution.id == null")
            }
    )
    <S extends Execution> S save(@NonNull S execution);

    @NonNull
    @Override
    @Cacheable(value = "executions")
    List<Execution> findAll();

    @NonNull
    @Override
    @Cacheable(value = "executions", key = "#executionId", unless = "#result == null")
    Optional<Execution> findById(@NonNull final String executionId);

    @NonNull
    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "executions", allEntries = true, beforeInvocation = true)
            }
    )
    void deleteById(@NonNull final String executionId);

    @NonNull
    @Override
    Page<Execution> findAll(@Nullable final Specification<Execution> spec, @NonNull final Pageable pageable);

    List<Execution> findExecutionsByWorkflowId(@NonNull final String workflowId);

}
