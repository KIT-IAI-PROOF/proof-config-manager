package com.smateso.proof.application.interactors;

import com.smateso.proof.application.model.Request;
import edu.kit.iai.webis.proofmodels.Execution;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import java.util.List;

public interface IExecutionInteractor {

    Page<Execution> searchExecutions(@NonNull final Request request);

    Execution getExecution(@NonNull final String id);

    Execution createExecution(@NonNull final Execution execution, @NonNull final String sessionKey, @NonNull final String username);

    Execution updateExecution(@NonNull final String id, @NonNull final Execution execution, @NonNull final String sessionKey, @NonNull final String username);

    void deleteExecution(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username);

    List<Execution> getExecutions();
}
