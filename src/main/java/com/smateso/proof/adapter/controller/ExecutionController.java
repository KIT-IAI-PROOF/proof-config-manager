package com.smateso.proof.adapter.controller;

import com.smateso.proof.application.interactors.IExecutionInteractor;
import com.smateso.proof.application.model.Request;
import edu.kit.iai.webis.proofmodels.Execution;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/execution")
public class ExecutionController implements IExecutionController {

    private final IExecutionInteractor executionInteractor;

    public ExecutionController(@Qualifier("executionInteractor") final IExecutionInteractor executionInteractor) {
        this.executionInteractor = executionInteractor;
    }

    @Override
    public ResponseEntity<List<Execution>> getExecutions(final Request request) {
        final var result = this.executionInteractor.getExecutions(request);
        return ResponseEntity.ok(result.stream().toList());
    }

    @Override
    public ResponseEntity<Execution> getExecution(final String id) {
        return ResponseEntity.ok(this.executionInteractor.getExecution(id));
    }

    @Override
    public ResponseEntity<Execution> createExecution(final Execution execution) {
        return ResponseEntity.ok(this.executionInteractor.createExecution(execution));
    }

}
