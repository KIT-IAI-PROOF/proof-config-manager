/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smateso.proof.adapter.boundaries.controller.*;
import com.smateso.proof.adapter.boundaries.services.*;
import com.smateso.proof.adapter.repositories.*;
import com.smateso.proof.application.controller.*;
import com.smateso.proof.application.services.*;
import edu.kit.iai.webis.proofmodels.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.nio.file.Paths;

/**
 * Configuration class responsible for defining and managing beans used throughout the app.
 * This class centralizes the creation and configuration of various app components.
 */
@Slf4j
@Configuration
public class Beans {

    @Value("${file.storage.root}")
    private String fileStorageRoot;

    @Bean(value = "workflowInteractor", name = "workflowInteractor")
    public IWorkflowInteractor workflowInteractor(@Qualifier("workflowRepository") @NonNull final WorkflowRepository workflowRepository, @Qualifier("executionRepository") @NonNull final ExecutionRepository executionRepository, @Qualifier("messageInteractor") @NonNull final IMessageInteractor messageInteractor, @NonNull final WorkflowMapper workflowMapper, @NonNull final ExecutionMapper executionMapper) {
        return new WorkflowInteractor(messageInteractor, workflowRepository, executionRepository, executionMapper, workflowMapper);
    }

    @Bean(value = "blockInteractor", name = "blockInteractor")
    public IBlockInteractor blockInteractor(@Qualifier("blockRepository") @NonNull final BlockRepository blockRepository, @Qualifier("messageInteractor") @NonNull final IMessageInteractor messageInteractor, @NonNull final BlockMapper blockMapper) {
        return new BlockInteractor(messageInteractor, blockRepository, blockMapper);
    }

    @Bean(value = "templateInteractor", name = "templateInteractor")
    public ITemplateInteractor templateInteractor(@Qualifier("templateRepository") @NonNull final TemplateRepository templateRepository, @Qualifier("blockRepository") @NonNull final BlockRepository blockRepository, @Qualifier("workflowRepository") @NonNull final WorkflowRepository workflowRepository, @Qualifier("messageInteractor") @NonNull final IMessageInteractor messageInteractor, @NonNull final TemplateMapper templateMapper, @NonNull final WorkflowMapper workflowMapper) {
        return new TemplateInteractor(messageInteractor, templateRepository, workflowRepository, blockRepository, templateMapper, workflowMapper);
    }

    @Bean(value = "executionInteractor", name = "executionInteractor")
    public IExecutionInteractor executionInteractor(@Qualifier("executionRepository") @NonNull final ExecutionRepository executionRepository, @Qualifier("messageInteractor") @NonNull final IMessageInteractor messageInteractor, @NonNull final TemplateRepository templateRepository, @NonNull final TemplateMapper templateMapper, @NonNull final ExecutionMapper executionMapper, @NonNull final ObjectMapper objectMapper) {
        return new ExecutionInteractor(messageInteractor, executionRepository, templateRepository, executionMapper, templateMapper, Paths.get(this.fileStorageRoot), objectMapper);
    }

    @Bean(value = "attachmentInteractor", name = "attachmentInteractor")
    public IAttachmentInteractor attachmentInteractor(@Qualifier("attachmentRepository") @NonNull final AttachmentRepository attachmentRepository, @Qualifier("messageInteractor") @NonNull final IMessageInteractor messageInteractor, @NonNull final AttachmentMapper attachmentMapper) {
        return new AttachmentInteractor(messageInteractor, attachmentRepository, attachmentMapper);
    }

    @Bean(value = "programInteractor", name = "programInteractor")
    public IProgramInteractor programInteractor(@Qualifier("programRepository") @NonNull final ProgramRepository programRepository, @Qualifier("messageInteractor") @NonNull final IMessageInteractor messageInteractor, @NonNull final ProgramMapper programMapper) {
        return new ProgramInteractor(messageInteractor, programRepository, programMapper);
    }

    @Bean(value = "workflowController", name = "workflowController")
    public IWorkflowController workflowController(@Qualifier("workflowInteractor") @NonNull final IWorkflowInteractor workflowInteractor) {
        return new WorkflowController(workflowInteractor);
    }

    @Bean(value = "blockController", name = "blockController")
    public IBlockController blockController(@Qualifier("blockInteractor") @NonNull final IBlockInteractor blockInteractor) {
        return new BlockController(blockInteractor);
    }

    @Bean(value = "executionController", name = "executionController")
    public IExecutionController executionController(@Qualifier("executionInteractor") @NonNull final IExecutionInteractor executionInteractor) {
        return new ExecutionController(executionInteractor);
    }

    @Bean(value = "templateController", name = "templateController")
    public ITemplateController templateController(@Qualifier("templateInteractor") @NonNull final ITemplateInteractor templateInteractor) {
        return new TemplateController(templateInteractor);
    }

    @Bean(value = "attachmentController", name = "attachmentController")
    public IAttachmentController attachmentController(@Qualifier("attachmentInteractor") @NonNull IAttachmentInteractor attachmentInteractor) {
        return new AttachmentController(attachmentInteractor, Paths.get(this.fileStorageRoot));
    }

    @Bean(value = "programController", name = "programController")
    public IProgramController programController(@Qualifier("programInteractor") @NonNull final IProgramInteractor programInteractor) {
        return new ProgramController(programInteractor);
    }

    @Bean(value = "csrfController", name = "csrfController")
    public ICsrfController csrfController() {
        return new CsrfController();
    }

    @Bean(value = "messageController", name = "messageController")
    public IMessageController messageController() {
        return new MessageController();
    }

    @Bean(value = "fileController", name = "fileController")
    public IFileController fileController() {
        return new FileController(Paths.get(this.fileStorageRoot));
    }

    @Profile({"debug", "dev", "prod"})
    @Bean(value = "messageInteractor", name = "messageInteractor")
    public IMessageInteractor messageInteractor(@NonNull final SimpMessagingTemplate simpMessagingTemplate, @NonNull final AmqpAdmin amqpAdmin) {
        return new MessageInteractor(simpMessagingTemplate, amqpAdmin);
    }

    @Profile({"test"})
    @Bean(value = "messageInteractor", name = "messageInteractor")
    public IMessageInteractor messageInteractorTest(@NonNull final SimpMessagingTemplate simpMessagingTemplate) {
        return new MessageInteractor(simpMessagingTemplate, null);
    }

}
