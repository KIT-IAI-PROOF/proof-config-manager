package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IExecutionInteractor;
import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.ExecutionRepository;
import edu.kit.iai.webis.proofmodels.dao.ExecutionDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.List;

import static com.smateso.proof.adapter.model.Action.*;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createPageable;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createSpecification;
import static com.smateso.proof.application.utils.LogMessages.EXECUTION_DAO_NOT_FOUND_MESSAGE;
import static com.smateso.proof.application.utils.PropertyCopyHelper.copyEntityProperties;
import static java.util.Objects.requireNonNull;

@Slf4j
@Builder
@AllArgsConstructor
public class ExecutionInteractor implements IExecutionInteractor {

    private final IMessageInteractor messageInteractor;
    private final ExecutionRepository executionRepository;

    @Override
    public Page<ExecutionDao> searchExecutions(@NonNull final Request request) {
        final Specification<ExecutionDao> specification = createSpecification(request);
        final var pageable = createPageable(request);
        return this.executionRepository.findAll(specification, pageable);
    }

    @Override
    public List<ExecutionDao> getExecutions() {
        return this.executionRepository.findAll();
    }

    @Override
    public ExecutionDao getExecution(@NonNull final String id) {
        final var result = this.executionRepository.findById(id);
        if (result.isPresent()) return result.get();
        else throw new NotFoundException(EXECUTION_DAO_NOT_FOUND_MESSAGE.formatted(id));
    }

    @Override
    public ExecutionDao createExecution(@NonNull final ExecutionDao executionDao, @NonNull final String sessionKey, @NonNull final String username) {
        final var result = this.executionRepository.save(executionDao);
        this.messageInteractor.executionUpdate(requireNonNull(result.getId()), CREATED, sessionKey, username);
        return result;
    }

    @Override
    public ExecutionDao updateExecution(@NonNull final String id, @NonNull final ExecutionDao updatingExecution, @NonNull final String sessionKey, @NonNull final String username) {
        final var existingExecution = this.getExecution(id);
        final var result = this.executionRepository.save(copyEntityProperties(updatingExecution, existingExecution, true));
        this.messageInteractor.executionUpdate(requireNonNull(result.getId()), UPDATED, sessionKey, username);
        return result;
    }

    @Override
    public void deleteExecution(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        this.executionRepository.deleteById(id);
        this.messageInteractor.executionUpdate(id, DELETED, sessionKey, username);
    }

}
