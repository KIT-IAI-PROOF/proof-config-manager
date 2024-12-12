package com.smateso.proof.adapter.controller;

import com.smateso.proof.application.interactors.ITemplateInteractor;
import com.smateso.proof.application.model.Request;
import edu.kit.iai.webis.proofmodels.Template;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/template")
public class TemplateController implements ITemplateController {

    private final ITemplateInteractor templateInteractor;

    public TemplateController(@Qualifier("templateInteractor") final ITemplateInteractor templateInteractor) {
        this.templateInteractor = templateInteractor;
    }

    @Override
    public ResponseEntity<List<Template>> getTemplates(final Request request) {
        final var result = this.templateInteractor.getTemplates(request);
        return ResponseEntity.ok(result.stream().toList());
    }

    @Override
    public ResponseEntity<Template> getTemplate(final String id) {
        return ResponseEntity.ok(this.templateInteractor.getTemplate(id));
    }

    @Override
    public ResponseEntity<Template> createTemplate(final Template template) {
        return ResponseEntity.ok(this.templateInteractor.createTemplate(template));
    }

}
