/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.Execution;
import edu.kit.iai.webis.proofmodels.dto.Workflow;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IWorkflowInteractor {

    Page<Workflow> searchWorkflows(@NonNull final Request request);

    Workflow getWorkflow(@NonNull final String id);

    Workflow createWorkflow(@NonNull final Workflow workflow, @Nullable final String sessionKey, @NonNull final String username);

    Workflow updateWorkflow(@NonNull final String id, @NonNull final Workflow workflow, @Nullable final String sessionKey, @NonNull final String username);

    void deleteWorkflow(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username);

    List<Workflow> getWorkflows();

    List<Execution> getExecutionsOfWorkflow(@NonNull final String id);

}
