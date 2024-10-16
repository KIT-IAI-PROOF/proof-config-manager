package com.smateso.proof.adapter.controller;

import com.smateso.proof.application.interactors.IWorkflowInteractor;
import com.smateso.proof.application.model.Workflow;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/workflow")
public class WorkflowController implements IWorkflowController {

    private final IWorkflowInteractor workflowInteractor;

    public WorkflowController(@Qualifier("workflowInteractor") final IWorkflowInteractor workflowInteractor) {
        this.workflowInteractor = workflowInteractor;
    }

    @Override
    public ResponseEntity<List<Workflow>> getWorkflows() {
        return ResponseEntity.ok(this.workflowInteractor.getWorkflows());
    }

    @Override
    public ResponseEntity<Workflow> getWorkflow(final String uuid) {
        return ResponseEntity.ok(this.workflowInteractor.getWorkflow(uuid));
    }

}
