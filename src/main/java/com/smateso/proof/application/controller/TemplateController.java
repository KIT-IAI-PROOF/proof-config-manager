package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.ITemplateController;
import com.smateso.proof.adapter.boundaries.services.ITemplateInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.Template;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;

public class TemplateController implements ITemplateController {

    private final ITemplateInteractor templateInteractor;

    public TemplateController(@NonNull final ITemplateInteractor templateInteractor) {
        this.templateInteractor = templateInteractor;
    }

    @Override
    public ResponseEntity<List<Template>> searchTemplates(@NonNull final Request request) {
        final var result = this.templateInteractor.searchTemplates(request);
        return ResponseEntity.ok(result.stream().toList());
    }

    @Override
    public ResponseEntity<List<Template>> getTemplates() {
        return ResponseEntity.ok(this.templateInteractor.getTemplates());
    }

    @Override
    public ResponseEntity<Template> getTemplate(@NonNull final String id) {
        return ResponseEntity.ok(this.templateInteractor.getTemplate(id));
    }

    @Override
    public ResponseEntity<Template> createTemplate(@NonNull final Template template, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(this.templateInteractor.createTemplate(template, sessionKey, username));
    }

    @Override
    public ResponseEntity<Template> updateTemplate(@NonNull final String templateId, @NonNull final Template template, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(this.templateInteractor.updateTemplate(templateId, template, sessionKey, username));
    }

}
