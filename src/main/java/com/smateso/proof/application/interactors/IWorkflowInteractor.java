package com.smateso.proof.application.interactors;

import com.smateso.proof.application.model.Workflow;

import java.util.List;

public interface IWorkflowInteractor {

    List<Workflow> getWorkflows();

    Workflow getWorkflow(String id);

    Workflow createWorkflow(Workflow workflow);

    Workflow updateWorkflow(String id, Workflow workflow);

    void deleteWorkflow(String id);

}
