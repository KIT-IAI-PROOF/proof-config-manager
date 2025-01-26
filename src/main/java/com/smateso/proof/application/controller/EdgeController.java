package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IEdgeController;
import com.smateso.proof.adapter.boundaries.services.IEdgeInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.EdgeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.application.utils.PropertyCopyHelper.EdgePropertyCopyHelper;
import static com.smateso.proof.application.utils.PropertyCopyHelper.EdgePropertyCopyHelper.copyEdgeProperties;

public class EdgeController implements IEdgeController {

    private final IEdgeInteractor edgeInteractor;

    public EdgeController(@NonNull final IEdgeInteractor edgeInteractor) {
        this.edgeInteractor = edgeInteractor;
    }

    @Override
    public ResponseEntity<List<EdgeDto>> searchEdges(@NonNull final Request request) {
        final var result = this.edgeInteractor.searchEdges(request).stream().map(EdgePropertyCopyHelper::copyEdgeProperties);
        return ResponseEntity.ok(result.toList());
    }

    @Override
    public ResponseEntity<List<EdgeDto>> getEdges() {
        final var edges = this.edgeInteractor.getEdges();
        final var result = edges.stream().map(EdgePropertyCopyHelper::copyEdgeProperties).toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<EdgeDto> getEdge(final String id) {
        return ResponseEntity.ok(copyEdgeProperties(this.edgeInteractor.getEdge(id)));
    }

    @Override
    public ResponseEntity<EdgeDto> createEdge(@NonNull final EdgeDto edge, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(copyEdgeProperties(this.edgeInteractor.createEdge(copyEdgeProperties(edge), sessionKey, username)));
    }

    @Override
    public ResponseEntity<EdgeDto> updateEdge(@NonNull final String edgeId, @NonNull final EdgeDto edge, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(copyEdgeProperties(this.edgeInteractor.updateEdge(edgeId, copyEdgeProperties(edge), sessionKey, username)));
    }

    @Override
    public ResponseEntity<Void> deleteEdge(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.edgeInteractor.deleteEdge(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
