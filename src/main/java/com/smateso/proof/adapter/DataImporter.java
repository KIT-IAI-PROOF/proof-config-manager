package com.smateso.proof.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smateso.proof.adapter.repositories.WorkflowRepository;
import edu.kit.iai.webis.proofmodels.dao.WorkflowDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
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
@Profile({"dev", "prod"})
public class DataImporter implements CommandLineRunner {

    @Value("${app.name}")
    private String appName;

    private final Environment environment;
    private final AmqpAdmin amqpAdmin;
    private final WorkflowRepository workflowRepository;
    private final ObjectMapper objectMapper;

    public DataImporter(Environment environment, final AmqpAdmin amqpAdmin, final WorkflowRepository workflowRepository, final ObjectMapper objectMapper) {
        this.environment = environment;
        this.amqpAdmin = amqpAdmin;
        this.workflowRepository = workflowRepository;
        this.objectMapper = objectMapper;
    }

    public void declareLogQueue() {
        final var queue = QueueBuilder.durable("logs." + appName).build();
        final var binding = BindingBuilder.bind(queue).to(new TopicExchange("logs")).with("logs." + appName);
        this.amqpAdmin.declareQueue(queue);
        this.amqpAdmin.declareBinding(binding);
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
            this.declareLogQueue();
            final var resource = new ClassPathResource("import.json");
            final var inputStream = resource.getInputStream();
            final var workflow = objectMapper.readValue(inputStream, WorkflowDao.class);
            workflowRepository.saveAll(List.of(workflow));
        } catch (Exception e) {
            log.info("No files present to import.");
        }
    }

}
