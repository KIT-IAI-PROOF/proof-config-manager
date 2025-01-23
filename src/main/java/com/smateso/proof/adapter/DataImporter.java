package com.smateso.proof.adapter;

import com.smateso.proof.adapter.repositories.TemplateRepository;
import edu.kit.iai.webis.proofmodels.dao.SourceHandleDao;
import edu.kit.iai.webis.proofmodels.dao.TargetHandleDao;
import edu.kit.iai.webis.proofmodels.dao.TemplateDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * The DataImporter class is a Spring Component that implements the CommandLineRunner interface.
 * It serves as an entry point for executing custom logic immediately after application startup.
 * This class is responsible for clearing multiple cache entries to ensure that the application
 * starts with fresh and consistent data.
 */
@Component
public class DataImporter implements CommandLineRunner {


    private final TemplateRepository templateRepository;

    public DataImporter(@NonNull final TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    /**
     * Executes the main logic for the application, processing the provided arguments.
     * This method is triggered upon application startup and is responsible for evicting
     * multiple cache entries to ensure data consistency.
     *
     * @param args the command-line arguments passed to the application, typically used for configuration or processing directives
     */
    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "workflows", allEntries = true),
                    @CacheEvict(value = "executions", allEntries = true),
                    @CacheEvict(value = "templates", allEntries = true),
                    @CacheEvict(value = "blocks", allEntries = true),
            }
    )
    public void run(final String... args) {
        final var template1 = TemplateDao
                .builder()
                .id(UUID.randomUUID().toString())
                .color("#0C63E7")
                .type("block")
                .description("Reader")
                .label("Reader")
                .build();
        template1.setSourceHandles(List.of(
        ));
        template1.setTargetHandles(List.of(
                TargetHandleDao
                        .builder()
                        .id(UUID.randomUUID().toString())
                        .label("o1")
                        .template(template1)
                        .build(),
                TargetHandleDao
                        .builder()
                        .id(UUID.randomUUID().toString())
                        .label("o2")
                        .template(template1)
                        .build()
        ));
        final var template2 = TemplateDao
                .builder()
                .id(UUID.randomUUID().toString())
                .color("#09A6F3")
                .type("block")
                .description("Writer")
                .label("Writer")
                .build();
        template2.setSourceHandles(List.of(
                SourceHandleDao
                        .builder()
                        .id(UUID.randomUUID().toString())
                        .label("i1")
                        .template(template1)
                        .build(),
                SourceHandleDao
                        .builder()
                        .id(UUID.randomUUID().toString())
                        .label("i2")
                        .template(template1)
                        .build()
        ));
        template2.setTargetHandles(List.of(
        ));
        this.templateRepository.save(template1);
        this.templateRepository.save(template2);
    }

}
