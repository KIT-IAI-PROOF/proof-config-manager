/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IExecutionController;
import com.smateso.proof.adapter.boundaries.services.IExecutionInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.Execution;
import edu.kit.iai.webis.proofmodels.paging.ExecutionPagingModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.smateso.proof.adapter.config.audit.AuditorProvider.getUsername;

@Builder
@AllArgsConstructor
public class ExecutionController implements IExecutionController {

    private final IExecutionInteractor executionInteractor;

    @Override
    public ResponseEntity<ExecutionPagingModel> searchExecutions(@NonNull final Request request) {
        final var executions = this.executionInteractor.searchExecutions(request);
        final var result = ExecutionPagingModel
                .builder()
                .rowCount(executions.getTotalElements())
                .results(executions.toList())
                .build();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Execution>> getExecutions() {
        final var executions = this.executionInteractor.getExecutions();
        return ResponseEntity.ok(executions);
    }

    @Override
    public ResponseEntity<Execution> getExecution(final String id) {
        final var execution = this.executionInteractor.getExecution(id);
        return ResponseEntity.ok(execution);
    }

    @Override
    public ResponseEntity<Execution> createExecution(@NonNull final Execution execution, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        final var result = this.executionInteractor.createExecution(execution, sessionKey, username);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Execution> updateExecution(@NonNull final String executionId, @NonNull final Execution execution, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        final var result = this.executionInteractor.updateExecution(executionId, execution, sessionKey, username);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteExecution(@NonNull final String id, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        this.executionInteractor.deleteExecution(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> exportExecution(@NonNull final String id) {
        final var resource = this.executionInteractor.exportExecution(id);
        final var fileName = "execution-" + id + ".proof";
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @Override
    public ResponseEntity<Void> importExecution(@NonNull final MultipartFile file, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        this.executionInteractor.importExecution(file, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
