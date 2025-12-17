package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.Execution;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IExecutionInteractor {

    Page<Execution> searchExecutions(@NonNull final Request request);

    Execution getExecution(@NonNull final String id);

    Execution createExecution(@NonNull final Execution execution, @Nullable final String sessionKey, @NonNull final String username);

    Execution updateExecution(@NonNull final String id, @NonNull final Execution execution, @Nullable final String sessionKey, @NonNull final String username);

    void deleteExecution(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username);

    List<Execution> getExecutions();
}
