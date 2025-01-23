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

import static java.util.stream.IntStream.range;

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
        final var templates = List.of(
                createTemplate("Reader", "#0D41E1", 2, 4),
                createTemplate("Transformer", "#0C63E7", 5, 6),
                createTemplate("Writer", "#0A85ED", 4, 2)
        );
        this.templateRepository.saveAll(templates);
    }

    private TemplateDao createTemplate(final String label, final String color, final int numberOfSourceHandles, final int numberOfTargetHandles) {
        final var template = TemplateDao
                .builder()
                .id(UUID.randomUUID().toString())
                .color(color)
                .type("block")
                .description(label)
                .label(label)
                .build();
        template.setTargetHandles(createTargetHandles(numberOfTargetHandles, template));
        template.setSourceHandles(createSourceHandles(numberOfSourceHandles, template));
        return template;
    }

    private List<SourceHandleDao> createSourceHandles(final int numberOfHandles, final TemplateDao template) {
        return range(0, numberOfHandles)
                .mapToObj(i -> SourceHandleDao
                        .builder()
                        .id(UUID.randomUUID().toString())
                        .label("i" + (i + 1))
                        .template(template)
                        .build()
                )
                .toList();
    }

    private List<TargetHandleDao> createTargetHandles(final int numberOfHandles, final TemplateDao template) {
        return range(0, numberOfHandles)
                .mapToObj(i -> TargetHandleDao
                        .builder()
                        .id(UUID.randomUUID().toString())
                        .label("o" + (i + 1))
                        .template(template)
                        .build()
                )
                .toList();
    }

}
