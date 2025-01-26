package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.boundaries.services.IWorkflowInteractor;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.WorkflowRepository;
import edu.kit.iai.webis.proofmodels.dao.WorkflowDao;
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
import static com.smateso.proof.application.utils.LogMessages.WORKFLOW_DAO_NOT_FOUND_MESSAGE;
import static com.smateso.proof.application.utils.PropertyCopyHelper.WorkflowPropertyCopyHelper.copyNonNullWorkflowProperties;
import static java.util.Objects.requireNonNull;

@Slf4j
@Builder
@AllArgsConstructor
public class WorkflowInteractor implements IWorkflowInteractor {

    private final IMessageInteractor messageInteractor;
    private final WorkflowRepository workflowRepository;

    @Override
    public Page<WorkflowDao> searchWorkflows(@NonNull final Request request) {
        final Specification<WorkflowDao> specification = createSpecification(request);
        final var pageable = createPageable(request);
        return this.workflowRepository.findAll(specification, pageable);
    }

    @Override
    public List<WorkflowDao> getWorkflows() {
        return this.workflowRepository.findAll();
    }

    @Override
    public WorkflowDao getWorkflow(@NonNull final String id) {
        final var result = this.workflowRepository.findById(id);
        if (result.isPresent()) return result.get();
        else throw new NotFoundException(WORKFLOW_DAO_NOT_FOUND_MESSAGE.formatted(id));
    }

    @Override
    public WorkflowDao createWorkflow(@NonNull final WorkflowDao workflowDao, @NonNull final String sessionKey, @NonNull final String username) {
        final var result = this.workflowRepository.save(workflowDao);
        this.messageInteractor.workflowUpdate(requireNonNull(result.getId()), CREATED, sessionKey, username);
        return result;
    }

    @Override
    public WorkflowDao updateWorkflow(@NonNull final String id, @NonNull final WorkflowDao updatingWorkflow, @NonNull final String sessionKey, @NonNull final String username) {
        final var existingWorkflow = this.getWorkflow(id);
        final var result = this.workflowRepository.save(copyNonNullWorkflowProperties(updatingWorkflow, existingWorkflow));
        this.messageInteractor.workflowUpdate(requireNonNull(result.getId()), UPDATED, sessionKey, username);
        return result;
    }

    @Override
    public void deleteWorkflow(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        this.workflowRepository.deleteById(id);
        this.messageInteractor.workflowUpdate(id, DELETED, sessionKey, username);
    }

}
