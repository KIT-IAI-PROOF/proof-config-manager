package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IProgramInteractor;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.ProgramRepository;
import edu.kit.iai.webis.proofmodels.dao.ProgramDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.List;

import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createPageable;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createSpecification;
import static com.smateso.proof.application.utils.LogMessages.PROGRAM_DAO_NOT_FOUND_MESSAGE;
import static com.smateso.proof.application.utils.PropertyCopyHelper.copyEntityProperties;

@Slf4j
@Builder
@AllArgsConstructor
public class ProgramInteractor implements IProgramInteractor {

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
    public ProgramDao createProgram(@NonNull final ProgramDao programDao, @NonNull final String sessionKey, @NonNull final String username) {
        return this.programRepository.save(programDao);
    }

    @Override
    public ProgramDao updateProgram(@NonNull final String id, @NonNull final ProgramDao updatingProgram, @NonNull final String sessionKey, @NonNull final String username) {
        final var existingProgram = this.getProgram(id);
        return this.programRepository.save(copyEntityProperties(updatingProgram, existingProgram, true, List.of()));
    }

    @Override
    public void deleteProgram(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        this.programRepository.deleteById(id);
    }

}
