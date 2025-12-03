package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.ConnectionDao;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IConnectionInteractor {

    Page<ConnectionDao> searchConnections(@NonNull final Request request);

    ConnectionDao getConnection(@NonNull final String id);

    ConnectionDao createConnection(@NonNull final ConnectionDao connectionDao, @Nullable final String sessionKey, @NonNull final String username);

    ConnectionDao updateConnection(@NonNull final String id, @NonNull final ConnectionDao connectionDao, @Nullable final String sessionKey, @NonNull final String username);

    void deleteConnection(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username);

    List<ConnectionDao> getConnections();

}
