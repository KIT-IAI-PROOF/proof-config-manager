package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.ProgramDao;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IProgramInteractor {

    Page<ProgramDao> searchPrograms(@NonNull final Request request);

    ProgramDao getProgram(@NonNull final String id);

    ProgramDao createProgram(@NonNull final ProgramDao programDao, @Nullable final String sessionKey, @NonNull final String username);

    ProgramDao updateProgram(@NonNull final String id, @NonNull final ProgramDao programDao, @Nullable final String sessionKey, @NonNull final String username);

    void deleteProgram(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username);

    List<ProgramDao> getPrograms();

}
