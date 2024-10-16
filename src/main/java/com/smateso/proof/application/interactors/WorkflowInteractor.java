package com.smateso.proof.application.interactors;

import com.smateso.proof.application.model.Workflow;
import com.smateso.proof.application.repositories.WorkflowRepository;

import java.util.List;

public class WorkflowInteractor implements IWorkflowInteractor {

    private final WorkflowRepository workflowRepository;

    public WorkflowInteractor(final WorkflowRepository workflowRepository) {
        this.workflowRepository = workflowRepository;
    }

    @Override
    public List<Workflow> getWorkflows() {
        return this.workflowRepository.findAll();
    }

    @Override
    public Workflow getWorkflow(final String id) {
        return this.workflowRepository.findById(id).orElseThrow();
    }

    @Override
    public Workflow createWorkflow(final Workflow workflow) {
        return this.workflowRepository.save(workflow);
    }

    @Override
    public Workflow updateWorkflow(String uuid, Workflow workflow) {
        final var existingWorkflow = this.workflowRepository.findById(uuid).orElseThrow();
        workflow.setUuid(existingWorkflow.getUuid());
        workflow.setId(existingWorkflow.getId());
        return this.workflowRepository.save(workflow);
    }

    @Override
    public void deleteWorkflow(final String uuid) {
        this.workflowRepository.deleteById(uuid);
    }

}
