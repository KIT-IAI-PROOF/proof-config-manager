package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.Block;
import edu.kit.iai.webis.proofmodels.Edge;
import edu.kit.iai.webis.proofmodels.Execution;
import edu.kit.iai.webis.proofmodels.Workflow;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import java.util.List;

public interface IWorkflowInteractor {

    Page<Workflow> searchWorkflows(@NonNull final Request request);

    Workflow getWorkflow(@NonNull final String id);

    Workflow createWorkflow(@NonNull final Workflow workflow, @NonNull final String sessionKey, @NonNull final String username);

    Workflow updateWorkflow(@NonNull final String id, @NonNull final Workflow workflow, @NonNull final String sessionKey, @NonNull final String username);

    void deleteWorkflow(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username);

    List<Workflow> getWorkflows();

    List<Block> getBlocks(@NonNull final String id);

    List<Edge> getEdges(@NonNull final String id);

    List<Execution> getExecutions(@NonNull final String id);

}
