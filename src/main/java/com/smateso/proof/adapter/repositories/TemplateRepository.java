package com.smateso.proof.adapter.repositories;

import edu.kit.iai.webis.proofmodels.Template;
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
@CacheConfig(cacheNames = "templates")
public interface TemplateRepository extends JpaRepository<Template, String>, JpaSpecificationExecutor<Template> {

    @NonNull
    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "templates", allEntries = true, beforeInvocation = true),
            },
            put = {
                    @CachePut(cacheNames = "templates", key = "#template.id", unless = "#template.id == null")
            }
    )
    <S extends Template> S save(@NonNull S template);

    @NonNull
    @Override
    @Cacheable(value = "templates")
    List<Template> findAll();

    @NonNull
    @Override
    @Cacheable(value = "templates", key = "#templateId", unless = "#result == null")
    Optional<Template> findById(@NonNull final String templateId);

    @NonNull
    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "templates", allEntries = true, beforeInvocation = true),
            }
    )
    void deleteById(@NonNull final String templateId);

    @NonNull
    @Override
    Page<Template> findAll(@Nullable final Specification<Template> spec, @NonNull final Pageable pageable);

}
