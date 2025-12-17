package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.boundaries.services.IWorkflowInteractor;
import com.smateso.proof.adapter.exceptions.AlreadyExistsException;
import com.smateso.proof.adapter.exceptions.InUseException;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.ExecutionRepository;
import com.smateso.proof.adapter.repositories.WorkflowRepository;
import edu.kit.iai.webis.proofmodels.dao.WorkflowDao;
import edu.kit.iai.webis.proofmodels.dto.Execution;
import edu.kit.iai.webis.proofmodels.dto.Workflow;
import edu.kit.iai.webis.proofmodels.mapper.ExecutionMapper;
import edu.kit.iai.webis.proofmodels.mapper.WorkflowMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.smateso.proof.adapter.model.Action.*;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createPageable;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createSpecification;
import static com.smateso.proof.application.utils.LogMessages.*;
import static java.util.Objects.requireNonNull;

@Slf4j
@AllArgsConstructor
public class WorkflowInteractor implements IWorkflowInteractor {

    private static final String ENTITY_WORKFLOW = "workflows";
    private final IMessageInteractor messageInteractor;
    private final WorkflowRepository workflowRepository;
    private final ExecutionRepository executionRepository;
    private final ExecutionMapper executionMapper;
    private final WorkflowMapper workflowMapper;

    @Override
    public Page<Workflow> searchWorkflows(@NonNull final Request request) {
        final Specification<WorkflowDao> specification = createSpecification(request);
        final var pageable = createPageable(request);
        final var result = this.workflowRepository.findAll(specification, pageable);
        return result.map(this.workflowMapper::convert);
    }

    @Override
    public List<Workflow> getWorkflows() {
        final var result = this.workflowRepository.findAll();
        return this.workflowMapper.convert(result);
    }

    @Override
    public Workflow getWorkflow(@NonNull final String id) {
        final var result = this.workflowRepository.findById(id);
        if (result.isPresent()) return this.workflowMapper.convert(result.get());
        else throw new NotFoundException(WORKFLOW_DAO_NOT_FOUND_MESSAGE.formatted(id));
    }

    @Override
    public Workflow createWorkflow(@NonNull final Workflow workflow, @Nullable final String sessionKey, @NonNull final String username) {
        if (Objects.nonNull(workflow.getId()) && !workflowRepository.existsById(workflow.getId())) {
            final var result = this.workflowRepository.save(this.workflowMapper.convert(workflow));
            this.messageInteractor.sendUpdate(ENTITY_WORKFLOW, requireNonNull(result.getId()), CREATED, sessionKey, username);
            return this.workflowMapper.convert(result);
        } else {
            throw new AlreadyExistsException(WORKFLOW_DAO_EXISTS.formatted(workflow.getId()));
        }
    }

    @Override
    public Workflow updateWorkflow(@NonNull final String id, @NonNull final Workflow partial, @Nullable final String sessionKey, @NonNull final String username) {
        final var optionalWorkflow = this.workflowRepository.findById(id);
        if (optionalWorkflow.isPresent()) {
            final var workflow = optionalWorkflow.get();
            this.workflowMapper.patch(partial, workflow);
            final var result = this.workflowRepository.save(workflow);
            this.messageInteractor.sendUpdate(ENTITY_WORKFLOW, requireNonNull(result.getId()), UPDATED, sessionKey, username);
            return this.workflowMapper.convert(result);
        } else {
            throw new NotFoundException(WORKFLOW_DAO_NOT_FOUND_MESSAGE.formatted(id));
        }
    }

    @Override
    @Transactional
    public void deleteWorkflow(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username) {
        try {
            this.executionRepository.deleteByWorkflowId(id);
            this.workflowRepository.deleteById(id);
            this.messageInteractor.sendUpdate(ENTITY_WORKFLOW, id, DELETED, sessionKey, username);
        } catch (final DataIntegrityViolationException e) {
            throw new InUseException(WORKFLOW_DAO_IN_USE_MESSAGE.formatted(id));
        }
    }

    @Override
    public List<Execution> getExecutionsOfWorkflow(@NonNull String id) {
        final var result = this.executionRepository.findAllByWorkflowId(id);
        return this.executionMapper.convert(result);
    }

}
