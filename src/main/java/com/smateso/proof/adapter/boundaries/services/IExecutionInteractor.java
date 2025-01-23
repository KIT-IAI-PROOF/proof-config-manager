package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.ExecutionDao;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import java.util.List;

public interface IExecutionInteractor {

    Page<ExecutionDao> searchExecutions(@NonNull final Request request);

    ExecutionDao getExecution(@NonNull final String id);

    ExecutionDao createExecution(@NonNull final ExecutionDao execution, @NonNull final String sessionKey, @NonNull final String username);

    ExecutionDao updateExecution(@NonNull final String id, @NonNull final ExecutionDao execution, @NonNull final String sessionKey, @NonNull final String username);

    void deleteExecution(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username);

    List<ExecutionDao> getExecutions();
}
