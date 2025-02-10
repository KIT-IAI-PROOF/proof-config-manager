package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IProgramController;
import com.smateso.proof.adapter.boundaries.services.IProgramInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.ProgramDao;
import edu.kit.iai.webis.proofmodels.dto.ProgramDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.nio.file.Path;
import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.application.utils.PropertyCopyHelper.copyEntityProperties;

@Builder
@AllArgsConstructor
public class ProgramController implements IProgramController {

    private final IProgramInteractor programInteractor;
    private final Path fileStorageRoot;

    @Override
    public ResponseEntity<List<ProgramDto>> searchPrograms(@NonNull final Request request) {
        final var programs = this.programInteractor.searchPrograms(request);
        final var result = programs.stream().map((final var program) -> copyEntityProperties(program, new ProgramDto(), false, List.of())).toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<ProgramDto>> getPrograms() {
        final var programs = this.programInteractor.getPrograms();
        final var result = programs.stream().map((final var program) -> copyEntityProperties(program, new ProgramDto(), false, List.of())).toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<ProgramDto> getProgram(final String id) {
        final var program = this.programInteractor.getProgram(id);
        final var result = copyEntityProperties(program, new ProgramDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<ProgramDto> createProgram(@NonNull final ProgramDto update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var program = this.programInteractor.createProgram(copyEntityProperties(update, new ProgramDao(), false, List.of()), sessionKey, username);
        final var result = copyEntityProperties(program, new ProgramDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<ProgramDto> updateProgram(@NonNull final String programId, @NonNull final ProgramDto update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var program = this.programInteractor.updateProgram(programId, copyEntityProperties(update, new ProgramDao(), false, List.of()), sessionKey, username);
        final var result = copyEntityProperties(program, new ProgramDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteProgram(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.programInteractor.deleteProgram(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
