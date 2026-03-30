/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.repositories;

import edu.kit.iai.webis.proofmodels.dao.ExecutionDao;
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
public interface ExecutionRepository extends JpaRepository<ExecutionDao, String>, JpaSpecificationExecutor<ExecutionDao> {

    @NonNull
    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "programs", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "attachments", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "blocks", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "connections", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "workflows", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "executions", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "templates", allEntries = true, beforeInvocation = true),
            },
            put = {
                    @CachePut(cacheNames = "executions", key = "#execution.id", unless = "#execution.id == null")
            }
    )
    <S extends ExecutionDao> S save(@NonNull S execution);

    @NonNull
    @Override
    @Cacheable(value = "executions")
    List<ExecutionDao> findAll();

    @NonNull
    @Override
    @Cacheable(value = "executions", key = "#executionId", unless = "#result == null")
    Optional<ExecutionDao> findById(@NonNull final String executionId);

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "programs", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "attachments", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "blocks", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "connections", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "workflows", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "executions", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "templates", allEntries = true, beforeInvocation = true),
            }
    )
    void deleteById(@NonNull final String executionId);

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "programs", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "attachments", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "blocks", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "connections", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "workflows", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "executions", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "templates", allEntries = true, beforeInvocation = true),
            }
    )
    void delete(@NonNull final ExecutionDao entity);

    @NonNull
    @Override
    Page<ExecutionDao> findAll(@Nullable final Specification<ExecutionDao> spec, @NonNull final Pageable pageable);

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "programs", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "attachments", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "blocks", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "connections", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "workflows", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "executions", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "templates", allEntries = true, beforeInvocation = true),
            }
    )
    void deleteByWorkflowId(@NonNull final String id);

    List<ExecutionDao> findAllByWorkflowId(final String workflowId);

}
