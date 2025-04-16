package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IProgramController;
import com.smateso.proof.adapter.boundaries.services.IProgramInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.ProgramDao;
import edu.kit.iai.webis.proofmodels.dto.Program;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.application.utils.PropertyCopyHelper.relationalCopyEntityProperties;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Builder
@AllArgsConstructor
public class ProgramController implements IProgramController {

    private final IProgramInteractor programInteractor;

    @NonNull
    private static Program attachLinks(@NonNull final Program program) {
        final var selfLink = linkTo(ProgramController.class).slash(program.getId()).withSelfRel();
        program.add(selfLink);
        return program;
    }

    @Override
    public ResponseEntity<List<Program>> searchPrograms(@NonNull final Request request) {
        final var programs = this.programInteractor.searchPrograms(request);
        final var result = programs
                .stream()
                .map((final var program) -> relationalCopyEntityProperties(program, new Program(), false, List.of()))
                .map(ProgramController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Program>> getPrograms() {
        final var programs = this.programInteractor.getPrograms();
        final var result = programs
                .stream()
                .map((final var program) -> relationalCopyEntityProperties(program, new Program(), false, List.of()))
                .map(ProgramController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Program>> exportPrograms() {
        return this.getPrograms();
    }

    @Override
    public ResponseEntity<Program> getProgram(final String id) {
        final var program = this.programInteractor.getProgram(id);
        final var result = relationalCopyEntityProperties(program, new Program(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Program> createProgram(@NonNull final Program update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var program = this.programInteractor.createProgram(relationalCopyEntityProperties(update, new ProgramDao(), false, List.of()), sessionKey, username);
        final var result = relationalCopyEntityProperties(program, new Program(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Program> updateProgram(@NonNull final String programId, @NonNull final Program update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var program = this.programInteractor.updateProgram(programId, relationalCopyEntityProperties(update, new ProgramDao(), false, List.of()), sessionKey, username);
        final var result = relationalCopyEntityProperties(program, new Program(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteProgram(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.programInteractor.deleteProgram(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
