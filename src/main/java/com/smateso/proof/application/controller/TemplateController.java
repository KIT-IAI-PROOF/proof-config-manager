/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.ITemplateController;
import com.smateso.proof.adapter.boundaries.services.ITemplateInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.Template;
import edu.kit.iai.webis.proofmodels.dto.Workflow;
import edu.kit.iai.webis.proofmodels.paging.TemplatePagingModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.audit.AuditorProvider.getUsername;

@Builder
@AllArgsConstructor
public class TemplateController implements ITemplateController {

    private final ITemplateInteractor templateInteractor;

    @Override
    public ResponseEntity<TemplatePagingModel> searchTemplates(@NonNull final Request request) {
        final var templates = this.templateInteractor.searchTemplates(request);
        final var result = TemplatePagingModel
                .builder()
                .rowCount(templates.getTotalElements())
                .results(templates.toList())
                .build();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Template>> getTemplates() {
        final var templates = this.templateInteractor.getTemplates();
        return ResponseEntity.ok(templates);
    }

    @Override
    public ResponseEntity<List<Template>> exportTemplates() {
        return this.getTemplates();
    }

    @Override
    public ResponseEntity<Template> getTemplate(final String id) {
        final var template = this.templateInteractor.getTemplate(id);
        return ResponseEntity.ok(template);
    }

    @Override
    public ResponseEntity<Template> createTemplate(@NonNull final Template template, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        final var result = this.templateInteractor.createTemplate(template, sessionKey, username);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Template> updateTemplate(@NonNull final String templateId, @NonNull final Template template, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        final var result = this.templateInteractor.updateTemplate(templateId, template, sessionKey, username);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteTemplate(@NonNull final String id, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        this.templateInteractor.deleteTemplate(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Workflow>> getWorkflowsForTemplate(@NonNull final String id) {
        final var blocks = this.templateInteractor.getWorkflowsForTemplate(id);
        return ResponseEntity.ok(blocks);
    }

}
