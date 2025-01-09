package com.smateso.proof.adapter.repositories;

import edu.kit.iai.webis.proofmodels.Edge;
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
public interface EdgeRepository extends JpaRepository<Edge, String>, JpaSpecificationExecutor<Edge> {

    @NonNull
    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "edges", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "workflows", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "executions", allEntries = true, beforeInvocation = true)
            },
            put = {
                    @CachePut(cacheNames = "edges", key = "#edge.id", unless = "#edge.id == null")
            }
    )
    <S extends Edge> S save(@NonNull S edge);

    @NonNull
    @Override
    @Cacheable(value = "edges")
    List<Edge> findAll();

    @NonNull
    @Override
    @Cacheable(value = "edges", key = "#edgeId", unless = "#result == null")
    Optional<Edge> findById(@NonNull final String edgeId);

    @NonNull
    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "edges", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "workflows", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "executions", allEntries = true, beforeInvocation = true)
            }
    )
    void deleteById(@NonNull final String edgeId);

    @NonNull
    @Override
    Page<Edge> findAll(@Nullable final Specification<Edge> spec, @NonNull final Pageable pageable);

    List<Edge> findEdgesByWorkflowId(@NonNull final String workflowId);

}
