package com.smateso.proof.adapter;

import com.smateso.proof.adapter.repositories.AttachmentRepository;
import com.smateso.proof.adapter.repositories.ProgramRepository;
import com.smateso.proof.adapter.repositories.TemplateRepository;
import edu.kit.iai.webis.proofmodels.dao.ProgramDao;
import edu.kit.iai.webis.proofmodels.dao.SourceHandleDao;
import edu.kit.iai.webis.proofmodels.dao.TargetHandleDao;
import edu.kit.iai.webis.proofmodels.dao.TemplateDao;
import edu.kit.iai.webis.proofmodels.dao.AttachmentDao;
import edu.kit.iai.webis.proofmodels.enums.ECommunicationType;
import edu.kit.iai.webis.proofmodels.enums.EIOType;
import edu.kit.iai.webis.proofmodels.enums.EWorkflowPhase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static edu.kit.iai.webis.proofmodels.enums.ECommunicationParadigm.STEPBASED;
import static edu.kit.iai.webis.proofmodels.enums.ERuntime.PYTHON;
import static java.util.stream.IntStream.range;

/**
 * The DataImporter class is a Spring Component that implements the CommandLineRunner interface.
 * It serves as an entry point for executing custom logic immediately after application startup.
 * This class is responsible for clearing multiple cache entries to ensure that the application
 * starts with fresh and consistent data.
 */
@Component
public class DataImporter implements CommandLineRunner {


    private final ProgramRepository programRepository;
    private final TemplateRepository templateRepository;
    private final AttachmentRepository attachmentRepository;

    public DataImporter(@NonNull final TemplateRepository templateRepository, @NonNull final ProgramRepository programRepository, @NonNull final AttachmentRepository attachmentRepository) {
        this.templateRepository = templateRepository;
        this.programRepository = programRepository;
        this.attachmentRepository = attachmentRepository;
    }

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
        final var attachments = List.of(
                createAttachment("Attachment1", "test1", "/proof/latest:test1"),
                createAttachment("Attachment2", "test2", "/proof/latest:test2"),
                createAttachment("Attachment3", "test3", "/proof/latest:test3")
        );
        this.templateRepository.saveAll(templates);
        this.attachmentRepository.saveAll(attachments);
    }

    private TemplateDao createTemplate(final String label, final String color, final int numberOfSourceHandles, final int numberOfTargetHandles) {
        final var template = TemplateDao
                .builder()
                .eventLifeCycles(List.of())
                .blockIODefinitions(List.of())
                .containerImage("proof-standard:latest")
                .id(UUID.randomUUID().toString())
                .color(color)
                .type("block")
                .description("A template for " + label)
                .communicationParadigm(STEPBASED)
                .startParameters(Map.of("path", "test"))
                .sourceHandles(List.of())
                .targetHandles(List.of())
                .label(label)
                .build();
        template.setProgram(createProgram());
        template.setTargetHandles(createTargetHandles(numberOfTargetHandles, template));
        template.setSourceHandles(createSourceHandles(numberOfSourceHandles, template));
        return template;
    }

    private ProgramDao createProgram() {
        final var program = ProgramDao
                .builder()
                .id(UUID.randomUUID().toString())
                .label("test")
                .description("test")
                .attachments(List.of())
                .entryPoint("test.py")
                .runtime(PYTHON)
                .build();
        return this.programRepository.save(program);
    }

    private List<SourceHandleDao> createSourceHandles(final int numberOfHandles, final TemplateDao template) {
        return range(0, numberOfHandles)
                .mapToObj(i -> SourceHandleDao
                        .builder()
                        .id(UUID.randomUUID().toString())
                        .label("i" + (i + 1))
                        .description("input " + (i + 1))
                        .communicationType(ECommunicationType.STEPBASED)
                        .type(EIOType.OBJECT)
                        .phase(EWorkflowPhase.EXECUTE)
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
                        .description("output " + (i + 1))
                        .communicationType(ECommunicationType.STEPBASED)
                        .phase(EWorkflowPhase.EXECUTE)
                        .type(EIOType.OBJECT)
                        .template(template)
                        .build()
                )
                .toList();
    }

    private AttachmentDao createAttachment(final String label, final String description, final String path) {
        return AttachmentDao
                .builder()
                .id(UUID.randomUUID().toString())
                .label(label)
                .description(description)
                .path(path)
                .build();
    }
}
