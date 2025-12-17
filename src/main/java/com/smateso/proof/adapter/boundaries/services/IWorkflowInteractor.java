package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.Execution;
import edu.kit.iai.webis.proofmodels.dto.WorkflowRequest;
import edu.kit.iai.webis.proofmodels.dto.WorkflowResponse;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IWorkflowInteractor {

    Page<WorkflowResponse> searchWorkflows(@NonNull final Request request);

    WorkflowResponse getWorkflow(@NonNull final String id);

    WorkflowResponse createWorkflow(@NonNull final WorkflowRequest workflow, @Nullable final String sessionKey, @NonNull final String username);

    WorkflowResponse updateWorkflow(@NonNull final String id, @NonNull final WorkflowRequest workflow, @Nullable final String sessionKey, @NonNull final String username);

    void deleteWorkflow(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username);

    List<WorkflowResponse> getWorkflows();

    List<Execution> getExecutionsOfWorkflow(@NonNull final String id);

}
