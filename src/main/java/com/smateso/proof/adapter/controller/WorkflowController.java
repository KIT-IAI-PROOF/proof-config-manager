package com.smateso.proof.adapter.controller;

import com.smateso.proof.application.model.Request;
import com.smateso.proof.application.services.IWorkflowInteractor;
import edu.kit.iai.webis.proofmodels.Workflow;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;

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
        final var username = getUsername(authentication);
        return ResponseEntity.ok(this.workflowInteractor.createWorkflow(workflow, sessionKey, username));
    }

    @Override
    public ResponseEntity<Workflow> updateWorkflow(@NonNull final String workflowId, @NonNull final Workflow workflow, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(this.workflowInteractor.updateWorkflow(workflowId, workflow, sessionKey, username));
    }

}
