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
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.application.utils.PropertyCopyHelper.relationalCopyEntityProperties;
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

    private static void convertStepSizeDefinitions(WorkflowDao source, WorkflowResponse target) {
        if (!Objects.isNull(source.getStepBasedConfig()) && !Objects.isNull(target.getStepBasedConfig())) {
            target.getStepBasedConfig().setStepSizeDefinitions(
                    Objects.requireNonNull(source.getStepBasedConfig().getStepSizeDefinitions()).entrySet().stream()
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    e -> relationalCopyEntityProperties(e.getValue(), new edu.kit.iai.webis.proofmodels.dto.StepSizeDefinition(), false, List.of())
                            ))
            );
        }
    }

    private static void convertStepSizeDefinitions(WorkflowRequest source, WorkflowDao target) {
        if (!Objects.isNull(source.getStepBasedConfig()) && !Objects.isNull(target.getStepBasedConfig())) {
            target.getStepBasedConfig().setStepSizeDefinitions(
                    source.getStepBasedConfig().getStepSizeDefinitions().entrySet().stream()
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    e -> relationalCopyEntityProperties(e.getValue(), new edu.kit.iai.webis.proofmodels.dao.StepSizeDefinitionDao(), false, List.of())
                            ))
            );
        }
    }

    @Override
    public ResponseEntity<List<WorkflowResponse>> searchWorkflows(@NonNull final Request request) {
        final var workflows = this.workflowInteractor.searchWorkflows(request);
        final var results = workflows
                .stream()
                .map((final var workflow) -> {
                    final var result = relationalCopyEntityProperties(workflow, new WorkflowResponse(), false, List.of());
                    convertStepSizeDefinitions(workflow, result);
                    return result;
                })
                .map(WorkflowController::attachLinks)
                .toList();
        return ResponseEntity.ok(results);
    }

    @Override
    public ResponseEntity<List<WorkflowResponse>> getWorkflows() {
        final var workflows = this.workflowInteractor.getWorkflows();
        final var results = workflows
                .stream()
                .map((final var workflow) -> {
                    final var result = relationalCopyEntityProperties(workflow, new WorkflowResponse(), false, List.of());
                    convertStepSizeDefinitions(workflow, result);
                    return result;
                })
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
        final var workflow = this.workflowInteractor.getWorkflow(id);
        final var result = relationalCopyEntityProperties(workflow, new WorkflowResponse(), false, List.of());
        convertStepSizeDefinitions(workflow, result);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<WorkflowResponse> createWorkflow(@NonNull final WorkflowRequest workflowToUpdate, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var convertedWorkflow = relationalCopyEntityProperties(workflowToUpdate, new WorkflowDao(), false, List.of());
        convertStepSizeDefinitions(workflowToUpdate, convertedWorkflow);
        final var workflow = this.workflowInteractor.createWorkflow(relationalCopyEntityProperties(convertedWorkflow, new WorkflowDao(), false, List.of()), sessionKey, username);
        final var result = relationalCopyEntityProperties(workflow, new WorkflowResponse(), false, List.of());
        convertStepSizeDefinitions(workflow, result);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<WorkflowResponse> updateWorkflow(@NonNull final String workflowId, @NonNull final WorkflowRequest workflowToUpdate, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var convertedWorkflow = relationalCopyEntityProperties(workflowToUpdate, new WorkflowDao(), false, List.of());
        convertStepSizeDefinitions(workflowToUpdate, convertedWorkflow);
        final var workflow = this.workflowInteractor.updateWorkflow(workflowId, convertedWorkflow, sessionKey, username);
        final var result = relationalCopyEntityProperties(workflow, new WorkflowResponse(), false, List.of());
        convertStepSizeDefinitions(workflow, result);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteWorkflow(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.workflowInteractor.deleteWorkflow(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
