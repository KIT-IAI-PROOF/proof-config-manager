package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.ITemplateController;
import com.smateso.proof.adapter.boundaries.services.ITemplateInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.TemplateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.application.utils.PropertyCopyHelper.TemplatePropertyCopyHelper;
import static com.smateso.proof.application.utils.PropertyCopyHelper.TemplatePropertyCopyHelper.copyTemplateProperties;

public class TemplateController implements ITemplateController {

    private final ITemplateInteractor templateInteractor;

    public TemplateController(@NonNull final ITemplateInteractor templateInteractor) {
        this.templateInteractor = templateInteractor;
    }

    @Override
    public ResponseEntity<List<TemplateDto>> searchTemplates(@NonNull final Request request) {
        final var result = this.templateInteractor.searchTemplates(request).stream().map(TemplatePropertyCopyHelper::copyTemplateProperties);
        return ResponseEntity.ok(result.toList());
    }

    @Override
    public ResponseEntity<List<TemplateDto>> getTemplates() {
        final var blocks = this.templateInteractor.getTemplates();
        final var result = blocks.stream().map(TemplatePropertyCopyHelper::copyTemplateProperties).toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<TemplateDto> getTemplate(final String id) {
        return ResponseEntity.ok(copyTemplateProperties(this.templateInteractor.getTemplate(id)));
    }

    @Override
    public ResponseEntity<TemplateDto> createTemplate(@NonNull final TemplateDto template, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(copyTemplateProperties(this.templateInteractor.createTemplate(copyTemplateProperties(template), sessionKey, username)));
    }

    @Override
    public ResponseEntity<TemplateDto> updateTemplate(@NonNull final String templateId, @NonNull final TemplateDto template, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(copyTemplateProperties(this.templateInteractor.updateTemplate(templateId, copyTemplateProperties(template), sessionKey, username)));
    }

    @Override
    public ResponseEntity<Void> deleteTemplate(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.templateInteractor.deleteTemplate(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
