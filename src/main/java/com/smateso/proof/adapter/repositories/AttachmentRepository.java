/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.repositories;

import edu.kit.iai.webis.proofmodels.dao.AttachmentDao;
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
@CacheConfig(cacheNames = "attachments")
public interface AttachmentRepository extends JpaRepository<AttachmentDao, String>, JpaSpecificationExecutor<AttachmentDao> {

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
                    @CachePut(cacheNames = "attachments", key = "#attachment.id", unless = "#attachment.id == null")
            }
    )
    <S extends AttachmentDao> S save(@NonNull S attachment);

    @NonNull
    @Override
    @Cacheable(value = "attachments")
    List<AttachmentDao> findAll();

    @NonNull
    @Override
    @Cacheable(value = "attachments", key = "#attachmentId", unless = "#result == null")
    Optional<AttachmentDao> findById(@NonNull final String attachmentId);

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
    void deleteById(@NonNull final String attachmentId);

    @NonNull
    @Override
    Page<AttachmentDao> findAll(@Nullable final Specification<AttachmentDao> spec, @NonNull final Pageable pageable);

}
