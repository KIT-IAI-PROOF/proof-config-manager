/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.Program;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IProgramInteractor {

    Page<Program> searchPrograms(@NonNull final Request request);

    Program getProgram(@NonNull final String id);

    Program createProgram(@NonNull final Program program, @Nullable final String sessionKey, @NonNull final String username);

    Program updateProgram(@NonNull final String id, @NonNull final Program program, @Nullable final String sessionKey, @NonNull final String username);

    void deleteProgram(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username);

    List<Program> getPrograms();

}
