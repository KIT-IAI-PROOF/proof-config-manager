/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.repositories;

import edu.kit.iai.webis.proofmodels.dao.ProgramDao;
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
@CacheConfig(cacheNames = "programs")
public interface ProgramRepository extends JpaRepository<ProgramDao, String>, JpaSpecificationExecutor<ProgramDao> {

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
                    @CachePut(cacheNames = "programs", key = "#program.id", unless = "#program.id == null")
            }
    )
    <S extends ProgramDao> S save(@NonNull S program);

    @NonNull
    @Override
    @Cacheable(value = "programs")
    List<ProgramDao> findAll();

    @NonNull
    @Override
    @Cacheable(value = "programs", key = "#programId", unless = "#result == null")
    Optional<ProgramDao> findById(@NonNull final String programId);

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
    void deleteById(@NonNull final String programId);

    @NonNull
    @Override
    Page<ProgramDao> findAll(@Nullable final Specification<ProgramDao> spec, @NonNull final Pageable pageable);

}
