package com.smateso.proof.adapter.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smateso.proof.adapter.helper.LocalDateTimeTypeAdapter;
import com.smateso.proof.adapter.helper.LocalDateTypeAdapter;
import com.smateso.proof.adapter.helper.OffsetDateTimeTypeAdapter;
import com.smateso.proof.application.interactors.BlockInteractor;
import com.smateso.proof.application.interactors.IBlockInteractor;
import com.smateso.proof.application.interactors.IWorkflowInteractor;
import com.smateso.proof.application.interactors.WorkflowInteractor;
import com.smateso.proof.application.repositories.BlockRepository;
import com.smateso.proof.application.repositories.WorkflowRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

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
    public IWorkflowInteractor workflowInteractor(@Qualifier("workflowRepository") final WorkflowRepository workflowRepository) {
        return new WorkflowInteractor(workflowRepository);
    }

    @Bean(value = "blockInteractor", name = "blockInteractor")
    public IBlockInteractor blockInteractor(@Qualifier("blockRepository") final BlockRepository blockRepository) {
        return new BlockInteractor(blockRepository);
    }

}
