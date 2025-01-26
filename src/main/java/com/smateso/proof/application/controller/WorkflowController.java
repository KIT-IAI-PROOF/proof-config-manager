package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IWorkflowController;
import com.smateso.proof.adapter.boundaries.services.IWorkflowInteractor;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.application.utils.PropertyCopyHelper.WorkflowPropertyCopyHelper;
import edu.kit.iai.webis.proofmodels.dto.WorkflowRequestDto;
import edu.kit.iai.webis.proofmodels.dto.WorkflowResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.application.utils.PropertyCopyHelper.WorkflowPropertyCopyHelper.copyWorkflowProperties;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
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
        final var result = this.workflowInteractor.searchWorkflows(request)
                .stream()
                .map(WorkflowPropertyCopyHelper::copyWorkflowProperties)
                .map(WorkflowController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<WorkflowResponseDto>> getWorkflows() {
        final var workflows = this.workflowInteractor.getWorkflows();
        final var result = workflows
                .stream()
                .map(WorkflowPropertyCopyHelper::copyWorkflowProperties)
                .map(WorkflowController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<WorkflowResponseDto> getWorkflow(@NonNull final String id) {
        final var result = copyWorkflowProperties(this.workflowInteractor.getWorkflow(id));
        return ResponseEntity.ok(attachLinks(result));
    }

    @Override
    public ResponseEntity<WorkflowResponseDto> createWorkflow(@NonNull final WorkflowRequestDto workflow, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var result = copyWorkflowProperties(this.workflowInteractor.createWorkflow(copyWorkflowProperties(workflow), sessionKey, username));
        return ResponseEntity.ok(attachLinks(result));
    }

    @Override
    public ResponseEntity<WorkflowResponseDto> updateWorkflow(@NonNull final String workflowId, @NonNull final WorkflowRequestDto workflow, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var result = copyWorkflowProperties(this.workflowInteractor.updateWorkflow(workflowId, copyWorkflowProperties(workflow), sessionKey, username));
        return ResponseEntity.ok(attachLinks(result));
    }

    @Override
    public ResponseEntity<Void> deleteWorkflow(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.workflowInteractor.deleteWorkflow(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
