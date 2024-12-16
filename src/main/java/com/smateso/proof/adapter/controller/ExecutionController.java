package com.smateso.proof.adapter.controller;

import com.smateso.proof.application.interactors.IExecutionInteractor;
import com.smateso.proof.application.model.Request;
import edu.kit.iai.webis.proofmodels.Execution;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/execution")
public class ExecutionController implements IExecutionController {

    private final IExecutionInteractor executionInteractor;

    public ExecutionController(@Qualifier("executionInteractor") @NonNull final IExecutionInteractor executionInteractor) {
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
        final Jwt jwt = (Jwt) authentication.getPrincipal();
        final var username = jwt.getClaimAsString("preferred_username");
        return ResponseEntity.ok(this.executionInteractor.createExecution(execution, sessionKey, username));
    }

    @Override
    public ResponseEntity<Execution> updateExecution(@NonNull final String executionId, @NonNull final Execution execution, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final Jwt jwt = (Jwt) authentication.getPrincipal();
        final var username = jwt.getClaimAsString("preferred_username");
        return ResponseEntity.ok(this.executionInteractor.updateExecution(executionId, execution, sessionKey, username));
    }

}
