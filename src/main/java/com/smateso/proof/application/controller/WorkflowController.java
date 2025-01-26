package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IWorkflowController;
import com.smateso.proof.adapter.boundaries.services.IWorkflowInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.WorkflowDao;
import edu.kit.iai.webis.proofmodels.dto.WorkflowRequestDto;
import edu.kit.iai.webis.proofmodels.dto.WorkflowResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.application.utils.PropertyCopyHelper.copyEntityProperties;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Builder
@AllArgsConstructor
public class WorkflowController implements IWorkflowController {

    private final IWorkflowInteractor workflowInteractor;

    @NonNull
    private static WorkflowResponseDto attachLinks(@NonNull final WorkflowResponseDto workflow) {
        final var selfLink = linkTo(WorkflowController.class).slash(workflow.getId()).withSelfRel();
        workflow.add(selfLink);
        return workflow;
    }

    @Override
    public ResponseEntity<List<WorkflowResponseDto>> searchWorkflows(@NonNull final Request request) {
        final var workflows = this.workflowInteractor.searchWorkflows(request);
        final var result = workflows
                .stream()
                .map((final var workflow) -> copyEntityProperties(workflow, new WorkflowResponseDto(), false))
                .map(WorkflowController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<WorkflowResponseDto>> getWorkflows() {
        final var workflows = this.workflowInteractor.getWorkflows();
        final var result = workflows
                .stream()
                .map((final var workflow) -> copyEntityProperties(workflow, new WorkflowResponseDto(), false))
                .map(WorkflowController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<WorkflowResponseDto> getWorkflow(final String id) {
        final var workflow = this.workflowInteractor.getWorkflow(id);
        final var result = copyEntityProperties(workflow, new WorkflowResponseDto(), false);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<WorkflowResponseDto> createWorkflow(@NonNull final WorkflowRequestDto update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var workflow = this.workflowInteractor.createWorkflow(copyEntityProperties(update, new WorkflowDao(), false), sessionKey, username);
        final var result = copyEntityProperties(workflow, new WorkflowResponseDto(), false);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<WorkflowResponseDto> updateWorkflow(@NonNull final String workflowId, @NonNull final WorkflowRequestDto update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var workflow = this.workflowInteractor.updateWorkflow(workflowId, copyEntityProperties(update, new WorkflowDao(), false), sessionKey, username);
        final var result = copyEntityProperties(workflow, new WorkflowResponseDto(), false);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteWorkflow(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.workflowInteractor.deleteWorkflow(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
