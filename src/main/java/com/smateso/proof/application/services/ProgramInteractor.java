/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.boundaries.services.IProgramInteractor;
import com.smateso.proof.adapter.exceptions.AlreadyExistsException;
import com.smateso.proof.adapter.exceptions.InUseException;
import com.smateso.proof.adapter.exceptions.MismatchException;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.ProgramRepository;
import edu.kit.iai.webis.proofmodels.dao.ProgramDao;
import edu.kit.iai.webis.proofmodels.dto.Program;
import edu.kit.iai.webis.proofmodels.mapper.ProgramMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;

import static com.smateso.proof.adapter.model.Action.*;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createPageable;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createSpecification;
import static java.util.Objects.requireNonNull;

@Slf4j
@AllArgsConstructor
public class ProgramInteractor implements IProgramInteractor {

    private static final String ENTITY_PROGRAM = "programs";
    private final IMessageInteractor messageInteractor;
    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;

    @Override
    public Page<Program> searchPrograms(@NonNull final Request request) {
        final Specification<ProgramDao> specification = createSpecification(request);
        final var pageable = createPageable(request);
        final var result = this.programRepository.findAll(specification, pageable);
        return result.map(this.programMapper::convert);
    }

    @Override
    public List<Program> getPrograms() {
        final var result = this.programRepository.findAll();
        return this.programMapper.convert(result);
    }

    @Override
    public Program getProgram(@NonNull final String id) {
        final var result = this.programRepository.findById(id);
        if (result.isPresent()) return this.programMapper.convert(result.get());
        else throw new NotFoundException("Program with id %s not found".formatted(id));
    }

    @Override
    public Program createProgram(@NonNull final Program program, @Nullable final String sessionKey, @NonNull final String username) {
        if (Objects.nonNull(program.getId()) && !programRepository.existsById(program.getId())) {
            final var result = this.programRepository.save(this.programMapper.convert(program));
            this.messageInteractor.sendUpdate(ENTITY_PROGRAM, requireNonNull(result.getId()), CREATED, sessionKey, username);
            return this.programMapper.convert(result);
        } else {
            throw new AlreadyExistsException("Program with id %s already exists".formatted(program.getId()));
        }
    }

    @Override
    public Program updateProgram(@NonNull final String id, @NonNull final Program update, @Nullable final String sessionKey, @NonNull final String username) {
        if (!Objects.equals(id, update.getId())) {
            throw new MismatchException("ID mismatch: Path ID (%s) does not match Request ID (%s)".formatted(id, update.getId()));
        }
        if (this.programRepository.existsById(id)) {
            final var result = this.programRepository.save(this.programMapper.convert(update));
            this.messageInteractor.sendUpdate(ENTITY_PROGRAM, requireNonNull(result.getId()), UPDATED, sessionKey, username);
            return this.programMapper.convert(result);
        } else {
            throw new NotFoundException("Program with id %s not found".formatted(id));
        }
    }

    @Override
    public void deleteProgram(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username) {
        try {
            this.programRepository.deleteById(id);
            this.messageInteractor.sendUpdate(ENTITY_PROGRAM, id, DELETED, sessionKey, username);
        } catch (final DataIntegrityViolationException e) {
            throw new InUseException("Program with id %s is still in use by another entity".formatted(id));
        }
    }

}
