package com.smateso.proof.adapter.config;

import com.smateso.proof.adapter.boundaries.controller.*;
import com.smateso.proof.adapter.boundaries.services.*;
import com.smateso.proof.adapter.repositories.*;
import com.smateso.proof.application.controller.*;
import com.smateso.proof.application.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public IWorkflowInteractor workflowInteractor(
            @Qualifier("workflowRepository") @NonNull final WorkflowRepository workflowRepository,
            @Qualifier("messageInteractor") @NonNull final IMessageInteractor messageInteractor) {
        return WorkflowInteractor
                .builder()
                .workflowRepository(workflowRepository)
                .messageInteractor(messageInteractor)
                .build();
    }

    @Bean(value = "blockInteractor", name = "blockInteractor")
    public IBlockInteractor blockInteractor(
            @Qualifier("blockRepository") @NonNull final BlockRepository blockRepository,
            @Qualifier("messageInteractor") @NonNull final IMessageInteractor messageInteractor) {
        return BlockInteractor
                .builder()
                .blockRepository(blockRepository)
                .messageInteractor(messageInteractor)
                .build();
    }

    @Bean(value = "connectionInteractor", name = "connectionInteractor")
    public IConnectionInteractor connectionInteractor(
            @Qualifier("connectionRepository") @NonNull final ConnectionRepository connectionRepository,
            @Qualifier("messageInteractor") @NonNull final IMessageInteractor messageInteractor) {
        return ConnectionInteractor
                .builder()
                .connectionRepository(connectionRepository)
                .messageInteractor(messageInteractor)
                .build();
    }

    @Bean(value = "templateInteractor", name = "templateInteractor")
    public ITemplateInteractor templateInteractor(
            @Qualifier("templateRepository") @NonNull final TemplateRepository templateRepository,
            @Qualifier("messageInteractor") @NonNull final IMessageInteractor messageInteractor) {
        return TemplateInteractor
                .builder()
                .templateRepository(templateRepository)
                .messageInteractor(messageInteractor)
                .build();
    }

    @Bean(value = "executionInteractor", name = "executionInteractor")
    public IExecutionInteractor executionInteractor(
            @Qualifier("executionRepository") @NonNull final ExecutionRepository executionRepository,
            @Qualifier("messageInteractor") @NonNull final IMessageInteractor messageInteractor) {
        return ExecutionInteractor
                .builder()
                .executionRepository(executionRepository)
                .messageInteractor(messageInteractor)
                .build();
    }

    @Bean(value = "attachmentInteractor", name = "attachmentInteractor")
    public IAttachmentInteractor attachmentInteractor(
            @Qualifier("attachmentRepository") @NonNull final AttachmentRepository attachmentRepository,
            @Qualifier("messageInteractor") @NonNull final IMessageInteractor messageInteractor) {
        return AttachmentInteractor
                .builder()
                .messageInteractor(messageInteractor)
                .attachmentRepository(attachmentRepository)
                .build();
    }

    @Bean(value = "programInteractor", name = "programInteractor")
    public IProgramInteractor programInteractor(
            @Qualifier("programRepository") @NonNull final ProgramRepository programRepository,
            @Qualifier("messageInteractor") @NonNull final IMessageInteractor messageInteractor) {
        return ProgramInteractor
                .builder()
                .messageInteractor(messageInteractor)
                .programRepository(programRepository)
                .build();
    }

    @Bean(value = "messageInteractor", name = "messageInteractor")
    public IMessageInteractor messageInteractor(@NonNull final SimpMessagingTemplate simpMessagingTemplate) {
        return MessageInteractor
                .builder()
                .simpMessagingTemplate(simpMessagingTemplate)
                .build();
    }

    @Bean(value = "workflowController", name = "workflowController")
    public IWorkflowController workflowController(@Qualifier("workflowInteractor") IWorkflowInteractor workflowInteractor) {
        return WorkflowController
                .builder()
                .workflowInteractor(workflowInteractor)
                .build();
    }

    @Bean(value = "blockController", name = "blockController")
    public IBlockController blockController(@Qualifier("blockInteractor") IBlockInteractor blockInteractor) {
        return new BlockController(blockInteractor);
    }

    @Bean(value = "connectionController", name = "connectionController")
    public IConnectionController connectionController(@Qualifier("connectionInteractor") IConnectionInteractor connectionInteractor) {
        return new ConnectionController(connectionInteractor);
    }

    @Bean(value = "executionController", name = "executionController")
    public IExecutionController executionController(@Qualifier("executionInteractor") IExecutionInteractor executionInteractor) {
        return new ExecutionController(executionInteractor);
    }

    @Bean(value = "templateController", name = "templateController")
    public ITemplateController templateController(@Qualifier("templateInteractor") ITemplateInteractor templateInteractor) {
        return new TemplateController(templateInteractor);
    }

    @Bean(value = "attachmentController", name = "attachmentController")
    public IAttachmentController attachmentController(@Qualifier("attachmentInteractor") IAttachmentInteractor attachmentInteractor) {
        return AttachmentController
                .builder()
                .fileStorageRoot(Paths.get(this.fileStorageRoot))
                .attachmentInteractor(attachmentInteractor)
                .build();
    }

    @Bean(value = "programController", name = "programController")
    public IProgramController programController(@Qualifier("programInteractor") IProgramInteractor programInteractor) {
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

}
