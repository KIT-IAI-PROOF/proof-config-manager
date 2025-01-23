package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IWorkflowController;
import com.smateso.proof.adapter.boundaries.services.IWorkflowInteractor;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.application.utils.PropertyCopyHelper;
import edu.kit.iai.webis.proofmodels.dto.WorkflowDto;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.application.utils.PropertyCopyHelper.copyWorkflowProperties;

public class WorkflowController implements IWorkflowController {

    private final IWorkflowInteractor workflowInteractor;

    public WorkflowController(@NonNull final IWorkflowInteractor workflowInteractor) {
        this.workflowInteractor = workflowInteractor;
    }


    @Override
    public ResponseEntity<List<WorkflowDto>> searchWorkflows(@NonNull final Request request) {
        final var result = this.workflowInteractor.searchWorkflows(request).stream().map(PropertyCopyHelper::copyWorkflowProperties);
        return ResponseEntity.ok(result.toList());
    }

    @Override
    public ResponseEntity<List<WorkflowDto>> getWorkflows() {
        final var workflows = this.workflowInteractor.getWorkflows();
        final var result = workflows.stream().map(PropertyCopyHelper::copyWorkflowProperties).toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<WorkflowDto> getWorkflow(@NonNull final String id) {
        return ResponseEntity.ok(copyWorkflowProperties(this.workflowInteractor.getWorkflow(id)));
    }

    @Override
    public ResponseEntity<WorkflowDto> createWorkflow(@NonNull final WorkflowDto workflow, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(copyWorkflowProperties(this.workflowInteractor.createWorkflow(copyWorkflowProperties(workflow), sessionKey, username)));
    }

    @Override
    public ResponseEntity<WorkflowDto> updateWorkflow(@NonNull final String workflowId, @NonNull final WorkflowDto workflow, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(copyWorkflowProperties(this.workflowInteractor.updateWorkflow(workflowId, copyWorkflowProperties(workflow), sessionKey, username)));
    }

    @Override
    public ResponseEntity<Void> deleteWorkflow(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.workflowInteractor.deleteWorkflow(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
