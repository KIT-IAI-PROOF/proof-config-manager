package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IExecutionController;
import com.smateso.proof.adapter.boundaries.services.IExecutionInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.ExecutionDao;
import edu.kit.iai.webis.proofmodels.dto.ExecutionDto;
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
public class ExecutionController implements IExecutionController {

    private final IExecutionInteractor executionInteractor;

    @NonNull
    private static ExecutionDto attachLinks(@NonNull final ExecutionDto execution) {
        final var selfLink = linkTo(ExecutionController.class).slash(execution.getId()).withSelfRel();
        execution.add(selfLink);
        return execution;
    }

    @Override
    public ResponseEntity<List<ExecutionDto>> searchExecutions(@NonNull final Request request) {
        final var executions = this.executionInteractor.searchExecutions(request);
        final var result = executions
                .stream()
                .map((final var execution) -> copyEntityProperties(execution, new ExecutionDto(), false, List.of()))
                .map(ExecutionController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<ExecutionDto>> getExecutions() {
        final var executions = this.executionInteractor.getExecutions();
        final var result = executions
                .stream()
                .map((final var execution) -> copyEntityProperties(execution, new ExecutionDto(), false, List.of()))
                .map(ExecutionController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<ExecutionDto> getExecution(final String id) {
        final var execution = this.executionInteractor.getExecution(id);
        final var result = copyEntityProperties(execution, new ExecutionDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<ExecutionDto> createExecution(@NonNull final ExecutionDto update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var execution = this.executionInteractor.createExecution(copyEntityProperties(update, new ExecutionDao(), false, List.of()), sessionKey, username);
        final var result = copyEntityProperties(execution, new ExecutionDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<ExecutionDto> updateExecution(@NonNull final String executionId, @NonNull final ExecutionDto update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var execution = this.executionInteractor.updateExecution(executionId, copyEntityProperties(update, new ExecutionDao(), false, List.of()), sessionKey, username);
        final var result = copyEntityProperties(execution, new ExecutionDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteExecution(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.executionInteractor.deleteExecution(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
