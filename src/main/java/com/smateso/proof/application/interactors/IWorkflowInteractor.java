package com.smateso.proof.application.interactors;

import com.smateso.proof.application.model.Request;
import edu.kit.iai.webis.proofmodels.Workflow;
import org.springframework.data.domain.Page;

public interface IWorkflowInteractor {

    Page<Workflow> getWorkflows(final Request request);

    Workflow getWorkflow(String id);

    Workflow createWorkflow(Workflow workflow);

    Workflow updateWorkflow(String id, Workflow workflow);

    void deleteWorkflow(String id);

}
