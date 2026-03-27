/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.boundaries.services.IWorkflowInteractor;
import com.smateso.proof.adapter.exceptions.AlreadyExistsException;
import com.smateso.proof.adapter.exceptions.InUseException;
import com.smateso.proof.adapter.exceptions.MismatchException;
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

import java.util.List;
import java.util.Objects;

import static com.smateso.proof.adapter.model.Action.*;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createPageable;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createSpecification;
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
        else throw new NotFoundException("Workflow with id %s not found".formatted(id));
    }

    @Override
    public Workflow createWorkflow(@NonNull final Workflow workflow, @Nullable final String sessionKey, @NonNull final String username) {
        if (Objects.nonNull(workflow.getId()) && !workflowRepository.existsById(workflow.getId())) {
            final var result = this.workflowRepository.save(this.workflowMapper.convert(workflow));
            this.messageInteractor.sendUpdate(ENTITY_WORKFLOW, requireNonNull(result.getId()), CREATED, sessionKey, username);
            return this.workflowMapper.convert(result);
        } else {
            throw new AlreadyExistsException("Workflow with id %s already exists".formatted(workflow.getId()));
        }
    }

    @Override
    public Workflow updateWorkflow(@NonNull final String id, @NonNull final Workflow workflow, @Nullable final String sessionKey, @NonNull final String username) {
        if (!Objects.equals(id, workflow.getId())) {
            throw new MismatchException("ID mismatch: Path ID (%s) does not match Request ID (%s)".formatted(id, workflow.getId()));
        }
        if (this.workflowRepository.existsById(id)) {
            final var result = this.workflowRepository.save(this.workflowMapper.convert(workflow));
            this.messageInteractor.sendUpdate(ENTITY_WORKFLOW, requireNonNull(result.getId()), UPDATED, sessionKey, username);
            return this.workflowMapper.convert(result);
        } else {
            throw new NotFoundException("Workflow with id %s not found".formatted(id));
        }
    }

    @Override
    public void deleteWorkflow(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username) {
        try {
            this.workflowRepository.deleteById(id);
            this.messageInteractor.sendUpdate(ENTITY_WORKFLOW, id, DELETED, sessionKey, username);
        } catch (final DataIntegrityViolationException e) {
            throw new InUseException("Workflow with id %s is still in use by another entity".formatted(id), e);
        }
    }

    @Override
    public List<Execution> getExecutionsOfWorkflow(@NonNull String id) {
        final var result = this.executionRepository.findAllByWorkflowId(id);
        return this.executionMapper.convert(result);
    }

}
