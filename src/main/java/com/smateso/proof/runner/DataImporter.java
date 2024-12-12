package com.smateso.proof.runner;

import com.smateso.proof.application.repositories.ExecutionRepository;
import com.smateso.proof.application.repositories.TemplateRepository;
import com.smateso.proof.application.repositories.WorkflowRepository;
import edu.kit.iai.webis.proofmodels.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static edu.kit.iai.webis.proofmodels.BlockStatus.CREATED;
import static java.util.UUID.randomUUID;

@Component
public class DataImporter implements CommandLineRunner {

    private final WorkflowRepository workflowRepository;
    private final TemplateRepository templateRepository;
    private final ExecutionRepository executionRepository;

    public DataImporter(final WorkflowRepository workflowRepository, TemplateRepository templateRepository, ExecutionRepository executionRepository) {
        this.workflowRepository = workflowRepository;
        this.templateRepository = templateRepository;
        this.executionRepository = executionRepository;
    }

    @Override
    public void run(String... args) {

        final var template1 = Template.builder()
                .id(randomUUID().toString())
                .label("Influx Reader")
                .description("Influx Reader")
                .color("#0C63E7")
                .type("block")
                .build();
        template1.setSourceHandles(List.of(
                SourceHandle.builder()
                        .id(randomUUID().toString())
                        .build()
        ));
        template1.setTargetHandles(List.of());
        this.templateRepository.save(template1);

        final var template2 = Template.builder()
                .id(randomUUID().toString())
                .label("Influx Writer")
                .description("Influx Writer")
                .color("#0D41E1")
                .type("block")
                .build();
        template2.setSourceHandles(List.of());
        template2.setTargetHandles(List.of(
                TargetHandle.builder()
                        .id(randomUUID().toString())
                        .build()
        ));
        this.templateRepository.save(template2);

        final var template3 = Template.builder()
                .id(randomUUID().toString())
                .label("Transformer")
                .description("Transformer")
                .color("#0A85ED")
                .type("block")
                .build();
        template3.setSourceHandles(List.of(
                SourceHandle.builder()
                        .id(randomUUID().toString())
                        .build()
        ));
        template3.setTargetHandles(List.of(
                TargetHandle.builder()
                        .id(randomUUID().toString())
                        .build(),
                TargetHandle.builder()
                        .id(randomUUID().toString())
                        .build()
        ));
        this.templateRepository.save(template3);

        final var block1Id = randomUUID().toString();
        final var block2Id = randomUUID().toString();
        final var sourceHandleId = randomUUID().toString();
        final var targetHandleId = randomUUID().toString();

        final var workflow = Workflow.builder()
                .id(randomUUID().toString())
                .blocks(List.of(
                        Block.builder()
                                .id(block1Id)
                                .label("Influx Reader")
                                .description("Influx Reader")
                                .color("#0C63E7")
                                .type("block")
                                .position(Position.builder()
                                        .x(705)
                                        .y(195)
                                        .build()
                                )
                                .sourceHandles(List.of(
                                        SourceHandle.builder()
                                                .id(sourceHandleId)
                                                .build()
                                ))
                                .targetHandles(List.of())
                                .build(),
                        Block.builder()
                                .id(block2Id)
                                .label("Influx Writer")
                                .description("Influx Writer")
                                .color("#0D41E1")
                                .type("block")
                                .position(Position.builder()
                                        .x(990)
                                        .y(165)
                                        .build()
                                )
                                .sourceHandles(List.of())
                                .targetHandles(List.of(
                                        TargetHandle.builder()
                                                .id(targetHandleId)
                                                .build()
                                ))
                                .build()
                ))
                .edges(List.of(
                        Edge.builder()
                                .type("block")
                                .algorithm("bezier-catmull-rom")
                                .points(List.of())
                                .animated(false)
                                .id(randomUUID().toString())
                                .source(block1Id)
                                .target(block2Id)
                                .sourceHandle(sourceHandleId)
                                .targetHandle(targetHandleId)
                                .build()
                ))
                .build();
        this.workflowRepository.save(workflow);

        final var execution = Execution.builder()
                .id(randomUUID().toString())
                .workflow(workflow)
                .status(CREATED)
                .startedAt(LocalDateTime.now().toString())
                .stoppedAt(LocalDateTime.now().toString())
                .build();
        execution.createdBy = "admin";
        this.executionRepository.save(execution);
    }

}
