/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.repositories;

import edu.kit.iai.webis.proofmodels.dao.ConnectionDao;
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
@CacheConfig(cacheNames = "connections")
public interface ConnectionRepository extends JpaRepository<ConnectionDao, String>, JpaSpecificationExecutor<ConnectionDao> {

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
                    @CachePut(cacheNames = "connections", key = "#connection.id", unless = "#connection.id == null")
            }
    )
    <S extends ConnectionDao> S save(@NonNull S connection);

    @NonNull
    @Override
    @Cacheable(value = "connections")
    List<ConnectionDao> findAll();

    @NonNull
    @Override
    @Cacheable(value = "connections", key = "#connectionId", unless = "#result == null")
    Optional<ConnectionDao> findById(@NonNull final String connectionId);

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
    void deleteById(@NonNull final String connectionId);

    @NonNull
    @Override
    Page<ConnectionDao> findAll(@Nullable final Specification<ConnectionDao> spec, @NonNull final Pageable pageable);

}
