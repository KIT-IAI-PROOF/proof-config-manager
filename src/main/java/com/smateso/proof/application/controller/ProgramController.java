/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IProgramController;
import com.smateso.proof.adapter.boundaries.services.IProgramInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.Program;
import edu.kit.iai.webis.proofmodels.paging.ProgramPagingModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.audit.AuditorProvider.getUsername;

@Builder
@AllArgsConstructor
public class ProgramController implements IProgramController {

    private final IProgramInteractor programInteractor;

    @Override
    public ResponseEntity<ProgramPagingModel> searchPrograms(@NonNull final Request request) {
        final var programs = this.programInteractor.searchPrograms(request);
        final var result = ProgramPagingModel
                .builder()
                .rowCount(programs.getTotalElements())
                .results(programs.toList())
                .build();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Program>> getPrograms() {
        final var programs = this.programInteractor.getPrograms();
        return ResponseEntity.ok(programs);
    }

    @Override
    public ResponseEntity<List<Program>> exportPrograms() {
        return this.getPrograms();
    }

    @Override
    public ResponseEntity<Program> getProgram(final String id) {
        final var program = this.programInteractor.getProgram(id);
        return ResponseEntity.ok(program);
    }

    @Override
    public ResponseEntity<Program> createProgram(@NonNull final Program program, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        final var result = this.programInteractor.createProgram(program, sessionKey, username);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Program> updateProgram(@NonNull final String programId, @NonNull final Program program, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        final var result = this.programInteractor.updateProgram(programId, program, sessionKey, username);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteProgram(@NonNull final String id, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        this.programInteractor.deleteProgram(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
