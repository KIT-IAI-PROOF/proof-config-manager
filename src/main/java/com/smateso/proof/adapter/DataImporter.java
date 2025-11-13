package com.smateso.proof.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smateso.proof.adapter.repositories.WorkflowRepository;
import edu.kit.iai.webis.proofmodels.dao.WorkflowDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * The DataImporter class is a Spring Component that implements the CommandLineRunner interface.
 * It serves as an entry point for executing custom logic immediately after application startup.
 * This class is responsible for clearing multiple cache entries to ensure that the application
 * starts with fresh and consistent data.
 */
@Slf4j
@Component
@Profile({"dev", "prod", "debug"})
public class DataImporter implements CommandLineRunner {

    private final CacheManager cacheManager;
    private final WorkflowRepository workflowRepository;
    private final ObjectMapper objectMapper;

    public DataImporter(final CacheManager cacheManager, final WorkflowRepository workflowRepository, final ObjectMapper objectMapper) {
        this.cacheManager = cacheManager;
        this.workflowRepository = workflowRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public void run(final String... args) {
        this.evictAllCaches();
        try {
            final var resource = new ClassPathResource("import.json");
            final var inputStream = resource.getInputStream();
            final var workflow = objectMapper.readValue(inputStream, WorkflowDao.class);
            workflowRepository.saveAll(List.of(workflow));
        } catch (Exception e) {
            log.info("No files present to import.");
        }
    }

    public void evictAllCaches() {
        this.cacheManager.getCacheNames().forEach(cacheName -> requireNonNull(cacheManager.getCache(cacheName)).clear());
    }

}
