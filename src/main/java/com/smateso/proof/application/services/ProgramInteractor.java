package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.boundaries.services.IProgramInteractor;
import com.smateso.proof.adapter.exceptions.InUseException;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.ProgramRepository;
import edu.kit.iai.webis.proofmodels.dao.ProgramDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

import static com.smateso.proof.adapter.model.Action.*;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createPageable;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createSpecification;
import static com.smateso.proof.application.utils.LogMessages.PROGRAM_DAO_IN_USE_MESSAGE;
import static com.smateso.proof.application.utils.LogMessages.PROGRAM_DAO_NOT_FOUND_MESSAGE;
import static com.smateso.proof.application.utils.PropertyCopyHelper.deepCopyEntityProperties;
import static java.util.Objects.requireNonNull;

@Slf4j
@Builder
@AllArgsConstructor
public class ProgramInteractor implements IProgramInteractor {

    private static final List<String> COMPLEX_FIELDS = List.of("attachments");
    private static final String ENTITY_PROGRAM = "programs";
    private final IMessageInteractor messageInteractor;
    private final ProgramRepository programRepository;

    @Override
    public Page<ProgramDao> searchPrograms(@NonNull final Request request) {
        final Specification<ProgramDao> specification = createSpecification(request);
        final var pageable = createPageable(request);
        return this.programRepository.findAll(specification, pageable);
    }

    @Override
    public List<ProgramDao> getPrograms() {
        return this.programRepository.findAll();
    }

    @Override
    public ProgramDao getProgram(@NonNull final String id) {
        final var result = this.programRepository.findById(id);
        if (result.isPresent()) return result.get();
        else throw new NotFoundException(PROGRAM_DAO_NOT_FOUND_MESSAGE.formatted(id));
    }

    @Override
    public ProgramDao createProgram(@NonNull final ProgramDao programDao, @Nullable final String sessionKey, @NonNull final String username) {
        final var result = this.programRepository.save(programDao);
        this.messageInteractor.sendUpdate(ENTITY_PROGRAM, requireNonNull(result.getId()), CREATED, sessionKey, username);
        return result;
    }

    @Override
    public ProgramDao updateProgram(@NonNull final String id, @NonNull final ProgramDao partial, @Nullable final String sessionKey, @NonNull final String username) {
        final var optionalProgram = this.programRepository.findById(id);
        if (optionalProgram.isPresent()) {
            final var program = optionalProgram.get();
            deepCopyEntityProperties(partial, program, true, List.of(), COMPLEX_FIELDS);
            final var result = this.programRepository.save(program);
            this.messageInteractor.sendUpdate(ENTITY_PROGRAM, requireNonNull(result.getId()), UPDATED, sessionKey, username);
            return result;
        } else {
            final var result = this.programRepository.save(partial);
            this.messageInteractor.sendUpdate(ENTITY_PROGRAM, requireNonNull(result.getId()), UPDATED, sessionKey, username);
            return result;
        }
    }

    @Override
    public void deleteProgram(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username) {
        try {
            this.programRepository.deleteById(id);
            this.messageInteractor.sendUpdate(ENTITY_PROGRAM, id, DELETED, sessionKey, username);
        } catch (final DataIntegrityViolationException e) {
            throw new InUseException(PROGRAM_DAO_IN_USE_MESSAGE.formatted(id));
        }
    }

}
