package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.WorkflowDao;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import java.util.List;

public interface IWorkflowInteractor {

    Page<WorkflowDao> searchWorkflows(@NonNull final Request request);

    WorkflowDao getWorkflow(@NonNull final String id);

    WorkflowDao createWorkflow(@NonNull final WorkflowDao workflow, @NonNull final String sessionKey, @NonNull final String username);

    WorkflowDao updateWorkflow(@NonNull final String id, @NonNull final WorkflowDao workflow, @NonNull final String sessionKey, @NonNull final String username);

    void deleteWorkflow(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username);

    List<WorkflowDao> getWorkflows();

}
