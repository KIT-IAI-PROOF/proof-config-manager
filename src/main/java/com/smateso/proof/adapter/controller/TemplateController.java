package com.smateso.proof.adapter.controller;

import com.smateso.proof.application.model.Request;
import com.smateso.proof.application.services.ITemplateInteractor;
import edu.kit.iai.webis.proofmodels.Template;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;

@RestController
@RequestMapping("/v1/template")
public class TemplateController implements ITemplateController {

    private final ITemplateInteractor templateInteractor;

    public TemplateController(@Qualifier("templateInteractor") @NonNull final ITemplateInteractor templateInteractor) {
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
