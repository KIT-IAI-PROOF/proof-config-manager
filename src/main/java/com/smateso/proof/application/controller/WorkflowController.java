package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IWorkflowController;
import com.smateso.proof.adapter.boundaries.services.IWorkflowInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.Block;
import edu.kit.iai.webis.proofmodels.Edge;
import edu.kit.iai.webis.proofmodels.Workflow;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;

public class WorkflowController implements IWorkflowController {

    private final IWorkflowInteractor workflowInteractor;

    public WorkflowController(@NonNull final IWorkflowInteractor workflowInteractor) {
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

    @Override
    public ResponseEntity<Void> deleteWorkflow(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.workflowInteractor.deleteWorkflow(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Block>> getBlocksByWorkflow(@NonNull final String id) {
        return ResponseEntity.ok(this.workflowInteractor.getBlocks(id));
    }

    @Override
    public ResponseEntity<List<Edge>> getEdgesByWorkflow(@NonNull final String id) {
        return ResponseEntity.ok(this.workflowInteractor.getEdges(id));
    }

}
