package com.smateso.proof.adapter;

import com.smateso.proof.adapter.repositories.TemplateRepository;
import com.smateso.proof.adapter.repositories.WorkflowRepository;
import edu.kit.iai.webis.proofmodels.dao.*;
import edu.kit.iai.webis.proofmodels.enums.ECommunicationType;
import edu.kit.iai.webis.proofmodels.enums.EIOType;
import edu.kit.iai.webis.proofmodels.enums.EWorkflowPhase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    private final TemplateRepository templateRepository;
    private final WorkflowRepository workflowRepository;

    public DataImporter(@NonNull final TemplateRepository templateRepository, WorkflowRepository workflowRepository) {
        this.templateRepository = templateRepository;
        this.workflowRepository = workflowRepository;
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
   
    }

    private WorkflowDao createWorkflow(final String name, final String label, final String description, final List<BlockDao> blocks) {
        final var workflow = WorkflowDao
                .builder()
                .name(name)
                .communicationParadigm(STEPBASED)
                .label(label)
                .description(description)
                .build();
        workflow.setStepBasedConfig(StepBasedConfigurationDao
                .builder()
                .workflow(workflow)
                .startPoint(0)
                .endPoint(0)
                .id(UUID.randomUUID().toString())
                .startTime(0L)
                .endTime(0L)
                .duration(0L)
                .defaultStepSize(0)
                .build());
        workflow.setBlocks(blocks);
        workflow.setConnections(List.of());
        return workflow;
    }

    private BlockDao createBlock(TemplateDao template) {
        final var block = BlockDao
                .builder()
                .id(UUID.randomUUID().toString())
                .label(template.getName() + LocalDateTime.now())
                .description(template.getDescription() + LocalDateTime.now())
                .type(template.getType())
                .startParameters(Map.of("test", "test"))
                .blockIODefinitions(List.of())
                .containerImage(template.getContainerImage())
                .templateId(template.getId())
                .templateName(template.getName())
                .build();
        block.setOutputs(Objects.requireNonNull(template.getOutputs()).stream().map(o -> OutputDao.builder()
                .id(UUID.randomUUID().toString())
                .label(o.getLabel())
                .block(block)
                .description(o.getDescription())
                .communicationType(o.getCommunicationType())
                .phase(o.getPhase())
                .type(o.getType())
                .build()).toList());
        block.setInputs(Objects.requireNonNull(template.getInputs()).stream().map(i -> InputDao.builder()
                .id(UUID.randomUUID().toString())
                .label(i.getLabel())
                .block(block)
                .description(i.getDescription())
                .communicationType(i.getCommunicationType())
                .phase(i.getPhase())
                .type(i.getType())
                .build()).toList());
        return block;
    }

    private TemplateDao createTemplate(final String name, final String color, final int numberOfOutputs, final int numberOfInputs) {
        final var template = TemplateDao
                .builder()
                .eventLifeCycles(List.of())
                .blockIODefinitions(List.of())
                .containerImage("proof-standard:latest")
                .id(UUID.randomUUID().toString())
                .color(color)
                .type("block")
                .description("A template for " + name)
                .communicationParadigm(STEPBASED)
                .startParameters(Map.of("path", "test"))
                .outputs(List.of())
                .inputs(List.of())
                .name(name)
                .build();
        template.setProgram(createProgram());
        template.setInputs(createInputs(numberOfInputs, template));
        template.setOutputs(createOutputs(numberOfOutputs, template));
        return template;
    }

    private ProgramDao createProgram() {
        return ProgramDao
                .builder()
                .id(UUID.randomUUID().toString())
                .label("test")
                .description("test")
                .attachments(List.of(
                        createAttachment("Attachment1", "test1", "/proof/latest:test1"),
                        createAttachment("Attachment2", "test2", "/proof/latest:test2"),
                        createAttachment("Attachment3", "test3", "/proof/latest:test3")
                ))
                .entryPoint("test.py")
                .runtime(PYTHON)
                .build();
    }

    private List<OutputDao> createOutputs(final int numberOfHandles, final TemplateDao template) {
        return range(0, numberOfHandles)
                .mapToObj(i -> OutputDao
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

    private List<InputDao> createInputs(final int numberOfHandles, final TemplateDao template) {
        return range(0, numberOfHandles)
                .mapToObj(i -> InputDao
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
