package com.smateso.proof.application.interactors;

import com.smateso.proof.application.model.Request;
import edu.kit.iai.webis.proofmodels.Execution;
import org.springframework.data.domain.Page;

public interface IExecutionInteractor {

    Page<Execution> getExecutions(final Request request);

    Execution getExecution(String id);

    Execution createExecution(Execution execution);

    Execution updateExecution(String id, Execution execution);

    void deleteExecution(String id);

}
