package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.ITemplateController;
import com.smateso.proof.adapter.boundaries.services.ITemplateInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.TemplateDao;
import edu.kit.iai.webis.proofmodels.dto.TemplateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.application.utils.PropertyCopyHelper.copyEntityProperties;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Builder
@AllArgsConstructor
public class TemplateController implements ITemplateController {

    private final ITemplateInteractor templateInteractor;

    @NonNull
    private static TemplateDto attachLinks(@NonNull final TemplateDto template) {
        final var selfLink = linkTo(TemplateController.class).slash(template.getId()).withSelfRel();
        template.add(selfLink);
        return template;
    }

    @Override
    public ResponseEntity<List<TemplateDto>> searchTemplates(@NonNull final Request request) {
        final var templates = this.templateInteractor.searchTemplates(request);
        final var result = templates
                .stream()
                .map((final var template) -> copyEntityProperties(template, new TemplateDto(), false, List.of()))
                .map(TemplateController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<TemplateDto>> getTemplates() {
        final var templates = this.templateInteractor.getTemplates();
        final var result = templates
                .stream()
                .map((final var template) -> copyEntityProperties(template, new TemplateDto(), false, List.of()))
                .map(TemplateController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<TemplateDto> getTemplate(final String id) {
        final var template = this.templateInteractor.getTemplate(id);
        final var result = copyEntityProperties(template, new TemplateDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<TemplateDto> createTemplate(@NonNull final TemplateDto update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var template = this.templateInteractor.createTemplate(copyEntityProperties(update, new TemplateDao(), false, List.of()), sessionKey, username);
        final var result = copyEntityProperties(template, new TemplateDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<TemplateDto> updateTemplate(@NonNull final String templateId, @NonNull final TemplateDto update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var template = this.templateInteractor.updateTemplate(templateId, copyEntityProperties(update, new TemplateDao(), false, List.of()), sessionKey, username);
        final var result = copyEntityProperties(template, new TemplateDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteTemplate(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.templateInteractor.deleteTemplate(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
