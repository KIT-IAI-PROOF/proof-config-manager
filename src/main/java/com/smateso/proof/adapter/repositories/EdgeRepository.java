package com.smateso.proof.adapter.repositories;

import edu.kit.iai.webis.proofmodels.dao.EdgeDao;
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
@CacheConfig(cacheNames = "edges")
public interface EdgeRepository extends JpaRepository<EdgeDao, String>, JpaSpecificationExecutor<EdgeDao> {

    @NonNull
    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "programs", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "attachments", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "blocks", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "edges", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "workflows", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "executions", allEntries = true, beforeInvocation = true),
            },
            put = {
                    @CachePut(cacheNames = "edges", key = "#edge.id", unless = "#edge.id == null")
            }
    )
    <S extends EdgeDao> S save(@NonNull S edge);

    @NonNull
    @Override
    @Cacheable(value = "edges")
    List<EdgeDao> findAll();

    @NonNull
    @Override
    @Cacheable(value = "edges", key = "#edgeId", unless = "#result == null")
    Optional<EdgeDao> findById(@NonNull final String edgeId);

    @NonNull
    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "programs", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "attachments", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "blocks", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "edges", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "workflows", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "executions", allEntries = true, beforeInvocation = true),
            }
    )
    void deleteById(@NonNull final String edgeId);

    @NonNull
    @Override
    Page<EdgeDao> findAll(@Nullable final Specification<EdgeDao> spec, @NonNull final Pageable pageable);

    List<EdgeDao> findEdgesByWorkflowId(@NonNull final String workflowId);

}
