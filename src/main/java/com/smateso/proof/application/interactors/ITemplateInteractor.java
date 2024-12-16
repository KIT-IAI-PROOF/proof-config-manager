package com.smateso.proof.application.interactors;

import com.smateso.proof.application.model.Request;
import edu.kit.iai.webis.proofmodels.Template;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ITemplateInteractor {

    Page<Template> searchTemplates(@NonNull final Request request);

    Template getTemplate(@NonNull final String id);

    Template createTemplate(@NonNull final Template template, @NonNull final String sessionKey, @NonNull final String username);

    Template updateTemplate(@NonNull final String id, @NonNull final Template template, @NonNull final String sessionKey, @NonNull final String username);

    void deleteTemplate(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username);

    List<Template> getTemplates();
}
