package com.smateso.proof.runner;

import com.smateso.proof.application.model.Block;
import com.smateso.proof.application.model.Edge;
import com.smateso.proof.application.model.Handle;
import com.smateso.proof.application.model.Workflow;
import com.smateso.proof.application.repositories.WorkflowRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.UUID.randomUUID;

@Component
public class DataImporter implements CommandLineRunner {

    private final WorkflowRepository workflowRepository;

    public DataImporter(final WorkflowRepository workflowRepository) {
        this.workflowRepository = workflowRepository;
    }

    @Override
    public void run(String... args) {
        final var workflow = Workflow
                .builder()
                .id("Test")
                .uuid("696be7bb-33e8-4a47-8fc9-a0858f01c195")
                .build();
        final var block1 = Block.builder()
                .uuid(randomUUID().toString())
                .id("a")
                .label("a")
                .workflow(workflow)
                .description("a")
                .color("#000000")
                .type("block")
                .build();
        block1.setSourceHandles(List.of(
                Handle.builder()
                        .uuid(randomUUID().toString())
                        .id("a1")
                        .block(block1)
                        .build()
        ));
        block1.setTargetHandles(List.of(
                Handle.builder()
                        .uuid(randomUUID().toString())
                        .id("a2")
                        .block(block1)
                        .build()
        ));
        final var block2 = Block.builder()
                .uuid(randomUUID().toString())
                .id("b")
                .label("b")
                .workflow(workflow)
                .description("b")
                .color("#000000")
                .type("block")
                .build();
        block2.setSourceHandles(List.of(
                Handle.builder()
                        .uuid(randomUUID().toString())
                        .id("b1")
                        .block(block2)
                        .build()
        ));
        block2.setTargetHandles(List.of(
                Handle.builder()
                        .uuid(randomUUID().toString())
                        .id("b2")
                        .block(block2)
                        .build()
        ));
        final var blocks = List.of(block1, block2);
        final var edges = List.of(
                Edge.builder()
                        .uuid(randomUUID().toString())
                        .id("a-b")
                        .type("")
                        .source("a")
                        .target("b")
                        .workflow(workflow)
                        .sourceHandle("a2")
                        .targetHandle("b1")
                        .animated(true)
                        .build()
        );
        workflow.setBlocks(blocks);
        workflow.setEdges(edges);
        this.workflowRepository.save(workflow);
    }

}
