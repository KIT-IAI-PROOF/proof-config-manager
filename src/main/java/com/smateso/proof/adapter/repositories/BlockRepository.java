package com.smateso.proof.adapter.repositories;

import edu.kit.iai.webis.proofmodels.Block;
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
@CacheConfig(cacheNames = "blocks")
public interface BlockRepository extends JpaRepository<Block, String>, JpaSpecificationExecutor<Block> {

    @NonNull
    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "blocks", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "workflows", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "executions", allEntries = true, beforeInvocation = true)
            },
            put = {
                    @CachePut(cacheNames = "blocks", key = "#block.id", unless = "#block.id == null")
            }
    )
    <S extends Block> S save(@NonNull S block);

    @NonNull
    @Override
    @Cacheable(value = "blocks")
    List<Block> findAll();

    @NonNull
    @Override
    @Cacheable(value = "blocks", key = "#blockId", unless = "#result == null")
    Optional<Block> findById(@NonNull final String blockId);

    @NonNull
    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "blocks", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "workflows", allEntries = true, beforeInvocation = true),
                    @CacheEvict(cacheNames = "executions", allEntries = true, beforeInvocation = true)
            }
    )
    void deleteById(@NonNull final String blockId);

    @NonNull
    @Override
    Page<Block> findAll(@Nullable final Specification<Block> spec, @NonNull final Pageable pageable);

    List<Block> findBlocksByWorkflowId(@NonNull final String workflowId);

}
