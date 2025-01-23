package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.TemplateDao;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ITemplateInteractor {

    Page<TemplateDao> searchTemplates(@NonNull final Request request);

    TemplateDao getTemplate(@NonNull final String id);

    TemplateDao createTemplate(@NonNull final TemplateDao template, @NonNull final String sessionKey, @NonNull final String username);

    TemplateDao updateTemplate(@NonNull final String id, @NonNull final TemplateDao template, @NonNull final String sessionKey, @NonNull final String username);

    void deleteTemplate(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username);

    List<TemplateDao> getTemplates();
}
