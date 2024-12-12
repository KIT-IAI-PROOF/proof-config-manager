package com.smateso.proof.application.interactors;

import com.smateso.proof.application.model.Request;
import edu.kit.iai.webis.proofmodels.Template;
import org.springframework.data.domain.Page;

public interface ITemplateInteractor {

    Page<Template> getTemplates(final Request request);

    Template getTemplate(String id);

    Template createTemplate(Template template);

    Template updateTemplate(String id, Template template);

    void deleteTemplate(String id);

}
