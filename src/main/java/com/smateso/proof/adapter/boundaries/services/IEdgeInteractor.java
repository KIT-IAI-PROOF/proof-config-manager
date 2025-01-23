package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.EdgeDao;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import java.util.List;

public interface IEdgeInteractor {

    Page<EdgeDao> searchEdges(@NonNull final Request request);

    EdgeDao getEdge(@NonNull final String id);

    EdgeDao createEdge(@NonNull final EdgeDao edgeDao, @NonNull final String sessionKey, @NonNull final String username);

    EdgeDao updateEdge(@NonNull final String id, @NonNull final EdgeDao edgeDao, @NonNull final String sessionKey, @NonNull final String username);

    void deleteEdge(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username);

    List<EdgeDao> getEdges();

}
