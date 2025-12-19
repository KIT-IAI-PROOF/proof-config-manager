package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IExecutionInteractor;
import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.exceptions.AlreadyExistsException;
import com.smateso.proof.adapter.exceptions.InUseException;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.ExecutionRepository;
import edu.kit.iai.webis.proofmodels.dao.BlockDao;
import edu.kit.iai.webis.proofmodels.dao.ExecutionDao;
import edu.kit.iai.webis.proofmodels.dto.Execution;
import edu.kit.iai.webis.proofmodels.mapper.ExecutionMapper;
import lombok.AllArgsConstructor;
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
import static com.smateso.proof.application.utils.LogMessages.*;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@Slf4j
@AllArgsConstructor
public class ExecutionInteractor implements IExecutionInteractor {

    private static final String ENTITY_EXECUTION = "executions";
    private final IMessageInteractor messageInteractor;
    private final ExecutionRepository executionRepository;
    private final ExecutionMapper executionMapper;

    @Override
    public Page<Execution> searchExecutions(@NonNull final Request request) {
        final Specification<ExecutionDao> specification = createSpecification(request);
        final var pageable = createPageable(request);
        final var result = this.executionRepository.findAll(specification, pageable);
        return result.map(this.executionMapper::convert);
    }

    @Override
    public List<Execution> getExecutions() {
        final var result = this.executionRepository.findAll();
        return this.executionMapper.convert(result);
    }

    @Override
    public Execution getExecution(@NonNull final String id) {
        final var result = this.executionRepository.findById(id);
        if (result.isPresent()) return this.executionMapper.convert(result.get());
        else throw new NotFoundException(EXECUTION_DAO_NOT_FOUND_MESSAGE.formatted(id));
    }

    @Override
    public Execution createExecution(@NonNull final Execution execution, @Nullable final String sessionKey, @NonNull final String username) {
        if (nonNull(execution.getId()) && !executionRepository.existsById(execution.getId())) {
            final var result = this.executionRepository.save(this.executionMapper.convert(execution));
            this.messageInteractor.sendUpdate(ENTITY_EXECUTION, requireNonNull(result.getId()), CREATED, sessionKey, username);
            return this.executionMapper.convert(result);
        } else {
            throw new AlreadyExistsException(EXECUTION_DAO_EXISTS.formatted(execution.getId()));
        }
    }

    @Override
    public Execution updateExecution(@NonNull final String id, @NonNull final Execution partial, @Nullable final String sessionKey, @NonNull final String username) {
        final var optionalExecution = this.executionRepository.findById(id);
        if (optionalExecution.isPresent()) {
            final var execution = optionalExecution.get();
            this.executionMapper.patch(partial, execution);
            final var result = this.executionRepository.save(execution);
            this.messageInteractor.sendUpdate(ENTITY_EXECUTION, requireNonNull(result.getId()), UPDATED, sessionKey, username);
            return this.executionMapper.convert(result);
        } else {
            throw new NotFoundException(EXECUTION_DAO_NOT_FOUND_MESSAGE.formatted(id));
        }
    }

    @Override
    public void deleteExecution(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username) {
        try {
            final var optionalExecution = this.executionRepository.findById(id);
            if (optionalExecution.isPresent()) {
                final var execution = optionalExecution.get();
                this.executionRepository.delete(execution);
                if (nonNull(execution.getWorkflow())) {
                    if (nonNull(execution.getWorkflow().getBlocks())) {
                        this.messageInteractor.deleteLoggingQueues(execution.getWorkflow().getBlocks().stream().map(BlockDao::getId).toList());
                    }
                }
                this.messageInteractor.sendUpdate(ENTITY_EXECUTION, id, DELETED, sessionKey, username);
            }
        } catch (final DataIntegrityViolationException e) {
            throw new InUseException(EXECUTION_DAO_IN_USE_MESSAGE.formatted(id));
        }
    }

}
