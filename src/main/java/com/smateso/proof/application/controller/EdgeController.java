package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IEdgeController;
import com.smateso.proof.adapter.boundaries.services.IEdgeInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.EdgeDao;
import edu.kit.iai.webis.proofmodels.dto.Edge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.application.utils.PropertyCopyHelper.relationalCopyEntityProperties;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Builder
@AllArgsConstructor
public class EdgeController implements IEdgeController {

    private final IEdgeInteractor edgeInteractor;

    @NonNull
    private static Edge attachLinks(@NonNull final Edge edge) {
        final var selfLink = linkTo(EdgeController.class).slash(edge.getId()).withSelfRel();
        edge.add(selfLink);
        return edge;
    }

    @Override
    public ResponseEntity<List<Edge>> searchEdges(@NonNull final Request request) {
        final var edges = this.edgeInteractor.searchEdges(request);
        final var result = edges
                .stream()
                .map((final var edge) -> relationalCopyEntityProperties(edge, new Edge(), false, List.of()))
                .map(EdgeController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Edge>> getEdges() {
        final var edges = this.edgeInteractor.getEdges();
        final var result = edges
                .stream()
                .map((final var edge) -> relationalCopyEntityProperties(edge, new Edge(), false, List.of()))
                .map(EdgeController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Edge> getEdge(final String id) {
        final var edge = this.edgeInteractor.getEdge(id);
        final var result = relationalCopyEntityProperties(edge, new Edge(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Edge> createEdge(@NonNull final Edge update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var edge = this.edgeInteractor.createEdge(relationalCopyEntityProperties(update, new EdgeDao(), false, List.of()), sessionKey, username);
        final var result = relationalCopyEntityProperties(edge, new Edge(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Edge> updateEdge(@NonNull final String edgeId, @NonNull final Edge update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var edge = this.edgeInteractor.updateEdge(edgeId, relationalCopyEntityProperties(update, new EdgeDao(), false, List.of()), sessionKey, username);
        final var result = relationalCopyEntityProperties(edge, new Edge(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteEdge(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.edgeInteractor.deleteEdge(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
