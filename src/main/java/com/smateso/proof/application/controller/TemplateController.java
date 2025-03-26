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
import static com.smateso.proof.application.utils.PropertyCopyHelper.relationalCopyEntityProperties;
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
        final var result = templates
                .stream()
                .map((final var template) -> relationalCopyEntityProperties(template, new Template(), false, List.of()))
                .map(TemplateController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Template>> getTemplates() {
        final var templates = this.templateInteractor.getTemplates();
        final var result = templates
                .stream()
                .map((final var template) -> relationalCopyEntityProperties(template, new Template(), false, List.of()))
                .map(TemplateController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Template> getTemplate(final String id) {
        final var template = this.templateInteractor.getTemplate(id);
        final var result = relationalCopyEntityProperties(template, new Template(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Template> createTemplate(@NonNull final Template update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var template = this.templateInteractor.createTemplate(relationalCopyEntityProperties(update, new TemplateDao(), false, List.of()), sessionKey, username);
        final var result = relationalCopyEntityProperties(template, new Template(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Template> updateTemplate(@NonNull final String templateId, @NonNull final Template update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var template = this.templateInteractor.updateTemplate(templateId, relationalCopyEntityProperties(update, new TemplateDao(), false, List.of()), sessionKey, username);
        final var result = relationalCopyEntityProperties(template, new Template(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteTemplate(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.templateInteractor.deleteTemplate(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
