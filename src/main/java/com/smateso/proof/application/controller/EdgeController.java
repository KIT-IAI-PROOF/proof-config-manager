package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IEdgeController;
import com.smateso.proof.adapter.boundaries.services.IEdgeInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.EdgeDao;
import edu.kit.iai.webis.proofmodels.dto.EdgeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.application.utils.PropertyCopyHelper.copyEntityProperties;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Builder
@AllArgsConstructor
public class EdgeController implements IEdgeController {

    private final IEdgeInteractor edgeInteractor;

    @NonNull
    private static EdgeDto attachLinks(@NonNull final EdgeDto edge) {
        final var selfLink = linkTo(EdgeController.class).slash(edge.getId()).withSelfRel();
        edge.add(selfLink);
        return edge;
    }

    @Override
    public ResponseEntity<List<EdgeDto>> searchEdges(@NonNull final Request request) {
        final var edges = this.edgeInteractor.searchEdges(request);
        final var result = edges
                .stream()
                .map((final var edge) -> copyEntityProperties(edge, new EdgeDto(), false, List.of()))
                .map(EdgeController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<EdgeDto>> getEdges() {
        final var edges = this.edgeInteractor.getEdges();
        final var result = edges
                .stream()
                .map((final var edge) -> copyEntityProperties(edge, new EdgeDto(), false, List.of()))
                .map(EdgeController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<EdgeDto> getEdge(final String id) {
        final var edge = this.edgeInteractor.getEdge(id);
        final var result = copyEntityProperties(edge, new EdgeDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<EdgeDto> createEdge(@NonNull final EdgeDto update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var edge = this.edgeInteractor.createEdge(copyEntityProperties(update, new EdgeDao(), false, List.of()), sessionKey, username);
        final var result = copyEntityProperties(edge, new EdgeDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<EdgeDto> updateEdge(@NonNull final String edgeId, @NonNull final EdgeDto update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var edge = this.edgeInteractor.updateEdge(edgeId, copyEntityProperties(update, new EdgeDao(), false, List.of()), sessionKey, username);
        final var result = copyEntityProperties(edge, new EdgeDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteEdge(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.edgeInteractor.deleteEdge(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
