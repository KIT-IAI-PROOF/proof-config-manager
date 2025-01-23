package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IExecutionController;
import com.smateso.proof.adapter.boundaries.services.IExecutionInteractor;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.application.utils.PropertyCopyHelper;
import edu.kit.iai.webis.proofmodels.dto.ExecutionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.application.utils.PropertyCopyHelper.copyExecutionProperties;

public class ExecutionController implements IExecutionController {

    private final IExecutionInteractor executionInteractor;

    public ExecutionController(@NonNull final IExecutionInteractor executionInteractor) {
        this.executionInteractor = executionInteractor;
    }

    @Override
    public ResponseEntity<List<ExecutionDto>> searchExecutions(@NonNull final Request request) {
        final var result = this.executionInteractor.searchExecutions(request).stream().map(PropertyCopyHelper::copyExecutionProperties);
        return ResponseEntity.ok(result.toList());
    }

    @Override
    public ResponseEntity<List<ExecutionDto>> getExecutions() {
        return ResponseEntity.ok(this.executionInteractor.getExecutions().stream().map(PropertyCopyHelper::copyExecutionProperties).toList());
    }

    @Override
    public ResponseEntity<ExecutionDto> getExecution(final String id) {
        return ResponseEntity.ok(copyExecutionProperties(this.executionInteractor.getExecution(id)));
    }

    @Override
    public ResponseEntity<ExecutionDto> createExecution(@NonNull final ExecutionDto execution, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(copyExecutionProperties(this.executionInteractor.createExecution(copyExecutionProperties(execution), sessionKey, username)));
    }

    @Override
    public ResponseEntity<ExecutionDto> updateExecution(@NonNull final String executionId, @NonNull final ExecutionDto execution, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(copyExecutionProperties(this.executionInteractor.updateExecution(executionId, copyExecutionProperties(execution), sessionKey, username)));
    }

    @Override
    public ResponseEntity<Void> deleteExecution(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.executionInteractor.deleteExecution(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
