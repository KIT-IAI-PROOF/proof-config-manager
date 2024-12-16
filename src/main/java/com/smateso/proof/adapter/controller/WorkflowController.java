package com.smateso.proof.adapter.controller;

import com.smateso.proof.application.interactors.IWorkflowInteractor;
import com.smateso.proof.application.model.Request;
import edu.kit.iai.webis.proofmodels.Workflow;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/workflow")
public class WorkflowController implements IWorkflowController {

    private final IWorkflowInteractor workflowInteractor;

    public WorkflowController(@Qualifier("workflowInteractor") @NonNull final IWorkflowInteractor workflowInteractor) {
        this.workflowInteractor = workflowInteractor;
    }

    @Override
    public ResponseEntity<List<Workflow>> searchWorkflows(@NonNull final Request request) {
        final var result = this.workflowInteractor.searchWorkflows(request);
        return ResponseEntity.ok(result.stream().toList());
    }

    @Override
    public ResponseEntity<List<Workflow>> getWorkflows() {
        return ResponseEntity.ok(this.workflowInteractor.getWorkflows());
    }

    @Override
    public ResponseEntity<Workflow> getWorkflow(@NonNull final String id) {
        return ResponseEntity.ok(this.workflowInteractor.getWorkflow(id));
    }

    @Override
    public ResponseEntity<Workflow> createWorkflow(@NonNull final Workflow workflow, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final Jwt jwt = (Jwt) authentication.getPrincipal();
        final var username = jwt.getClaimAsString("preferred_username");
        return ResponseEntity.ok(this.workflowInteractor.createWorkflow(workflow, sessionKey, username));
    }

    @Override
    public ResponseEntity<Workflow> updateWorkflow(@NonNull final String workflowId, @NonNull final Workflow workflow, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final Jwt jwt = (Jwt) authentication.getPrincipal();
        final var username = jwt.getClaimAsString("preferred_username");
        return ResponseEntity.ok(this.workflowInteractor.updateWorkflow(workflowId, workflow, sessionKey, username));
    }

}
