/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Action;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface IMessageInteractor {

    void sendUpdate(@NonNull final String entity, @NonNull final String id, @NonNull final Action action, @Nullable final String sessionKey, @Nullable final String username);

    void deleteBlockLoggingQueues(@NonNull final Iterable<String> blockIds, @NonNull final String id);

    void deleteExecutionLoggingQueue(@NonNull final String id);

}
