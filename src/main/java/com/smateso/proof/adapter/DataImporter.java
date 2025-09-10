package com.smateso.proof.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smateso.proof.adapter.repositories.WorkflowRepository;
import edu.kit.iai.webis.proofmodels.dao.WorkflowDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The DataImporter class is a Spring Component that implements the CommandLineRunner interface.
 * It serves as an entry point for executing custom logic immediately after application startup.
 * This class is responsible for clearing multiple cache entries to ensure that the application
 * starts with fresh and consistent data.
 */
@Slf4j
@Component
public class DataImporter implements CommandLineRunner {

    private final WorkflowRepository workflowRepository;
    private final ObjectMapper objectMapper;

    public DataImporter(final WorkflowRepository workflowRepository, final ObjectMapper objectMapper) {
        this.workflowRepository = workflowRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "workflows", allEntries = true),
                    @CacheEvict(value = "executions", allEntries = true),
                    @CacheEvict(value = "templates", allEntries = true),
                    @CacheEvict(value = "blocks", allEntries = true),
                    @CacheEvict(value = "attachments", allEntries = true),
            }
    )
    public void run(final String... args) {
        try {
            final var resource = new ClassPathResource("import.json");
            final var inputStream = resource.getInputStream();
            final var workflow = objectMapper.readValue(inputStream, WorkflowDao.class);
            workflowRepository.saveAll(List.of(workflow));
        } catch (Exception e) {
            log.warn("Failed to auto import: %s".formatted(e.getMessage()));
        }
    }

}
