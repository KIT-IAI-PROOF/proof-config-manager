/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IWorkflowController;
import com.smateso.proof.adapter.boundaries.services.IWorkflowInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.Execution;
import edu.kit.iai.webis.proofmodels.dto.Workflow;
import edu.kit.iai.webis.proofmodels.paging.WorkflowPagingModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.audit.AuditorProvider.getUsername;

@Builder
@AllArgsConstructor
public class WorkflowController implements IWorkflowController {

    private final IWorkflowInteractor workflowInteractor;

    @Override
    public ResponseEntity<WorkflowPagingModel> searchWorkflows(@NonNull final Request request) {
        final var workflows = this.workflowInteractor.searchWorkflows(request);
        final var result = WorkflowPagingModel.builder().rowCount(workflows.getTotalElements()).results(workflows.toList()).build();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Workflow>> getWorkflows() {
        final var workflows = this.workflowInteractor.getWorkflows();
        return ResponseEntity.ok(workflows);
    }

    @Override
    public ResponseEntity<List<Workflow>> exportWorkflows() {
        return this.getWorkflows();
    }

    @Override
    public ResponseEntity<Workflow> getWorkflow(final String id) {
        final var workflow = this.workflowInteractor.getWorkflow(id);
        return ResponseEntity.ok(workflow);
    }

    @Override
    public ResponseEntity<Workflow> createWorkflow(@NonNull final Workflow workflow, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        final var result = this.workflowInteractor.createWorkflow(workflow, sessionKey, username);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Workflow> updateWorkflow(@NonNull final String workflowId, @NonNull final Workflow workflow, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        final var result = this.workflowInteractor.updateWorkflow(workflowId, workflow, sessionKey, username);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteWorkflow(@NonNull final String id, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        this.workflowInteractor.deleteWorkflow(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Execution>> getExecutionsOfWorkflow(@NonNull final String id) {
        final var executions = this.workflowInteractor.getExecutionsOfWorkflow(id);
        return ResponseEntity.ok(executions);
    }

}
