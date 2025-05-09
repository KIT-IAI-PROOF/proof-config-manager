package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IWorkflowController;
import com.smateso.proof.adapter.boundaries.services.IWorkflowInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.WorkflowDao;
import edu.kit.iai.webis.proofmodels.dto.WorkflowRequest;
import edu.kit.iai.webis.proofmodels.dto.WorkflowResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Builder
@AllArgsConstructor
public class WorkflowController implements IWorkflowController {

    private final IWorkflowInteractor workflowInteractor;

    @NonNull
    private static WorkflowResponse attachLinks(@NonNull final WorkflowResponse workflow) {
        final var selfLink = linkTo(WorkflowController.class).slash(workflow.getId()).withSelfRel();
        workflow.add(selfLink);
        return workflow;
    }

    @Override
    public ResponseEntity<List<WorkflowResponse>> searchWorkflows(@NonNull final Request request) {
        final var workflows = this.workflowInteractor.searchWorkflows(request);
        final var results = workflows
                .stream()
                .map(WorkflowDao::convert)
                .map(WorkflowController::attachLinks)
                .toList();
        return ResponseEntity.ok(results);
    }

    @Override
    public ResponseEntity<List<WorkflowResponse>> getWorkflows() {
        final var workflows = this.workflowInteractor.getWorkflows();
        final var results = workflows
                .stream()
                .map(WorkflowDao::convert)
                .map(WorkflowController::attachLinks)
                .toList();
        return ResponseEntity.ok(results);
    }

    @Override
    public ResponseEntity<List<WorkflowResponse>> exportWorkflows() {
        return this.getWorkflows();
    }

    @Override
    public ResponseEntity<WorkflowResponse> getWorkflow(final String id) {
        final var workflow = this.workflowInteractor.getWorkflow(id).convert();
        return ResponseEntity.ok(workflow);
    }

    @Override
    public ResponseEntity<WorkflowResponse> createWorkflow(@NonNull final WorkflowRequest workflow, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var result = this.workflowInteractor.createWorkflow(workflow.convert(), sessionKey, username).convert();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<WorkflowResponse> updateWorkflow(@NonNull final String workflowId, @NonNull final WorkflowRequest workflow, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var result = this.workflowInteractor.updateWorkflow(workflowId, workflow.convert(), sessionKey, username).convert();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteWorkflow(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.workflowInteractor.deleteWorkflow(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
