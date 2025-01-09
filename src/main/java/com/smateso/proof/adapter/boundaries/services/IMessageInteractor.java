package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Action;
import org.springframework.lang.NonNull;

public interface IMessageInteractor {

    void workflowUpdate(@NonNull final String id, @NonNull final Action action, @NonNull final String sessionKey, @NonNull final String username);

    void executionUpdate(@NonNull final String id, @NonNull final Action action, @NonNull final String sessionKey, @NonNull final String username);

    void templateUpdate(@NonNull final String id, @NonNull final Action action, @NonNull final String sessionKey, @NonNull final String username);

    void blockUpdate(@NonNull final String id, @NonNull final Action action, @NonNull final String sessionKey, @NonNull final String username);

}
