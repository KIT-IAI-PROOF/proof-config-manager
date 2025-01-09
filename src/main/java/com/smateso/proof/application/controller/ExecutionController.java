package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IExecutionController;
import com.smateso.proof.adapter.boundaries.services.IExecutionInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.Execution;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;

public class ExecutionController implements IExecutionController {

    private final IExecutionInteractor executionInteractor;

    public ExecutionController(@NonNull final IExecutionInteractor executionInteractor) {
        this.executionInteractor = executionInteractor;
    }

    @Override
    public ResponseEntity<List<Execution>> searchExecutions(@NonNull final Request request) {
        final var result = this.executionInteractor.searchExecutions(request);
        return ResponseEntity.ok(result.stream().toList());
    }

    @Override
    public ResponseEntity<List<Execution>> getExecutions() {
        return ResponseEntity.ok(this.executionInteractor.getExecutions());
    }

    @Override
    public ResponseEntity<Execution> getExecution(@NonNull final String id) {
        return ResponseEntity.ok(this.executionInteractor.getExecution(id));
    }

    @Override
    public ResponseEntity<Execution> createExecution(@NonNull final Execution execution, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(this.executionInteractor.createExecution(execution, sessionKey, username));
    }

    @Override
    public ResponseEntity<Execution> updateExecution(@NonNull final String executionId, @NonNull final Execution execution, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(this.executionInteractor.updateExecution(executionId, execution, sessionKey, username));
    }

}
