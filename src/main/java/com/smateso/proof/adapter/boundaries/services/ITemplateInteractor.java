/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.Template;
import edu.kit.iai.webis.proofmodels.dto.Workflow;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ITemplateInteractor {

    Page<Template> searchTemplates(@NonNull final Request request);

    Template getTemplate(@NonNull final String id);

    Template createTemplate(@NonNull final Template template, @Nullable final String sessionKey, @NonNull final String username);

    Template updateTemplate(@NonNull final String id, @NonNull final Template template, @Nullable final String sessionKey, @NonNull final String username);

    void deleteTemplate(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username);

    List<Template> getTemplates();

    List<Workflow> getWorkflowsForTemplate(@NonNull final String id);

}
