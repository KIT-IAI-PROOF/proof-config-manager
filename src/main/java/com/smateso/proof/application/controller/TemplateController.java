package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.ITemplateController;
import com.smateso.proof.adapter.boundaries.services.ITemplateInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.TemplateDao;
import edu.kit.iai.webis.proofmodels.dto.Template;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Builder
@AllArgsConstructor
public class TemplateController implements ITemplateController {

    private final ITemplateInteractor templateInteractor;

    @NonNull
    private static Template attachLinks(@NonNull final Template template) {
        final var selfLink = linkTo(TemplateController.class).slash(template.getId()).withSelfRel();
        template.add(selfLink);
        return template;
    }

    @Override
    public ResponseEntity<List<Template>> searchTemplates(@NonNull final Request request) {
        final var templates = this.templateInteractor.searchTemplates(request);
        final var results = templates
                .stream()
                .map(TemplateDao::convert)
                .map(TemplateController::attachLinks)
                .toList();
        return ResponseEntity.ok(results);
    }

    @Override
    public ResponseEntity<List<Template>> getTemplates() {
        final var templates = this.templateInteractor.getTemplates();
        final var results = templates
                .stream()
                .map(TemplateDao::convert)
                .map(TemplateController::attachLinks)
                .toList();
        return ResponseEntity.ok(results);
    }

    @Override
    public ResponseEntity<List<Template>> exportTemplates() {
        return this.getTemplates();
    }

    @Override
    public ResponseEntity<Template> getTemplate(final String id) {
        final var template = this.templateInteractor.getTemplate(id).convert();
        return ResponseEntity.ok(template);
    }

    @Override
    public ResponseEntity<Template> createTemplate(@NonNull final Template template, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var result = this.templateInteractor.createTemplate(template.convert(), sessionKey, username).convert();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Template> updateTemplate(@NonNull final String templateId, @NonNull final Template template, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var result = this.templateInteractor.updateTemplate(templateId, template.convert(), sessionKey, username).convert();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteTemplate(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.templateInteractor.deleteTemplate(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
