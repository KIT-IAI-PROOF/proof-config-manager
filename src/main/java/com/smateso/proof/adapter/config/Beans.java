package com.smateso.proof.adapter.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smateso.proof.adapter.boundaries.controller.*;
import com.smateso.proof.adapter.boundaries.services.*;
import com.smateso.proof.adapter.helper.LocalDateTimeTypeAdapter;
import com.smateso.proof.adapter.helper.LocalDateTypeAdapter;
import com.smateso.proof.adapter.helper.OffsetDateTimeTypeAdapter;
import com.smateso.proof.adapter.repositories.*;
import com.smateso.proof.application.controller.*;
import com.smateso.proof.application.services.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/**
 * Configuration class responsible for defining and managing beans used throughout the app.
 * This class centralizes the creation and configuration of various app components.
 */
@Configuration
public class Beans {

    @Value("${timezone}")
    private String timezone;

    @Bean(value = "gson", name = "gson")
    public Gson gson() {
        final var builder = new GsonBuilder();
        return builder
                .serializeSpecialFloatingPointValues()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter(this.timezone))
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeTypeAdapter(this.timezone))
                .setPrettyPrinting()
                .create();
    }


    @Bean(value = "workflowInteractor", name = "workflowInteractor")
    public IWorkflowInteractor workflowInteractor(
            @Qualifier("workflowRepository") @NonNull final WorkflowRepository workflowRepository,
            @Qualifier("edgeRepository") @NonNull final EdgeRepository edgeRepository,
            @Qualifier("blockRepository") @NonNull final BlockRepository blockRepository,
            @Qualifier("messageInteractor") @NonNull final IMessageInteractor IMessageInteractor) {
        return new WorkflowInteractor(workflowRepository, blockRepository, IMessageInteractor, edgeRepository);
    }

    @Bean(value = "blockInteractor", name = "blockInteractor")
    public IBlockInteractor blockInteractor(@Qualifier("blockRepository") @NonNull final BlockRepository blockRepository, @Qualifier("messageInteractor") @NonNull final IMessageInteractor IMessageInteractor) {
        return new BlockInteractor(blockRepository, IMessageInteractor);
    }

    @Bean(value = "templateInteractor", name = "templateInteractor")
    public ITemplateInteractor templateInteractor(@Qualifier("templateRepository") @NonNull final TemplateRepository templateRepository, @Qualifier("messageInteractor") @NonNull final IMessageInteractor IMessageInteractor) {
        return new TemplateInteractor(templateRepository, IMessageInteractor);
    }

    @Bean(value = "executionInteractor", name = "executionInteractor")
    public IExecutionInteractor executionInteractor(@Qualifier("executionRepository") @NonNull final ExecutionRepository executionRepository, @Qualifier("messageInteractor") @NonNull final IMessageInteractor IMessageInteractor) {
        return new ExecutionInteractor(executionRepository, IMessageInteractor);
    }

    @Bean(value = "messageInteractor", name = "messageInteractor")
    public IMessageInteractor messageInteractor(@NonNull final SimpMessagingTemplate simpMessagingTemplate) {
        return new MessageInteractor(simpMessagingTemplate);
    }

    @Bean(value = "workflowController", name = "workflowController")
    public IWorkflowController workflowController(@Qualifier("workflowInteractor") IWorkflowInteractor workflowInteractor) {
        return new WorkflowController(workflowInteractor);
    }

    @Bean(value = "blockController", name = "blockController")
    public IBlockController blockController(@Qualifier("blockInteractor") IBlockInteractor blockInteractor) {
        return new BlockController(blockInteractor);
    }

    @Bean(value = "executionController", name = "executionController")
    public IExecutionController executionController(@Qualifier("executionInteractor") IExecutionInteractor executionInteractor) {
        return new ExecutionController(executionInteractor);
    }

    @Bean(value = "templateController", name = "templateController")
    public ITemplateController templateController(@Qualifier("templateInteractor") ITemplateInteractor templateInteractor) {
        return new TemplateController(templateInteractor);
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
