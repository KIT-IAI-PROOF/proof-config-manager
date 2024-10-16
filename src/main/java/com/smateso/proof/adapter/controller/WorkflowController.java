package com.smateso.proof.adapter.controller;

import com.smateso.proof.application.model.Workflow;
import com.smateso.proof.application.repositories.WorkflowRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/workflow")
public class WorkflowController implements IWorkflowController {

    private final WorkflowRepository workflowRepository;

    public WorkflowController(WorkflowRepository workflowRepository) {
        this.workflowRepository = workflowRepository;
    }

    @Override
    public ResponseEntity<List<Workflow>> getWorkflows() {
        return ResponseEntity.ok(workflowRepository.findAll());
    }

    @Override
    public ResponseEntity<Workflow> getWorkflow(String id) {
        return ResponseEntity.ok(workflowRepository.findById(id).orElseThrow());
    }

}
