package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IConnectionController;
import com.smateso.proof.adapter.boundaries.services.IConnectionInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.ConnectionDao;
import edu.kit.iai.webis.proofmodels.dto.Connection;
import edu.kit.iai.webis.proofmodels.paging.ConnectionPagingModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.audit.AuditorProvider.getUsername;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Builder
@AllArgsConstructor
public class ConnectionController implements IConnectionController {

    private final IConnectionInteractor connectionInteractor;

    @NonNull
    private static Connection attachLinks(@NonNull final Connection connection) {
        final var selfLink = linkTo(ConnectionController.class).slash(connection.getId()).withSelfRel();
        connection.add(selfLink);
        return connection;
    }

    @Override
    public ResponseEntity<ConnectionPagingModel> searchConnections(@NonNull final Request request) {
        final var connections = this.connectionInteractor.searchConnections(request);
        final var result = ConnectionPagingModel
                .builder()
                .rowCount(connections.getTotalElements())
                .results(connections
                        .stream()
                        .map(ConnectionDao::convert)
                        .map(ConnectionController::attachLinks)
                        .toList())
                .build();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Connection>> getConnections() {
        final var connections = this.connectionInteractor.getConnections();
        final var results = connections
                .stream()
                .map(ConnectionDao::convert)
                .map(ConnectionController::attachLinks)
                .toList();
        return ResponseEntity.ok(results);
    }

    @Override
    public ResponseEntity<Connection> getConnection(final String id) {
        final var connection = this.connectionInteractor.getConnection(id).convert();
        return ResponseEntity.ok(connection);
    }

    @Override
    public ResponseEntity<Connection> createConnection(@NonNull final Connection connection, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var result = this.connectionInteractor.createConnection(connection.convert(), sessionKey, username).convert();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Connection> updateConnection(@NonNull final String connectionId, @NonNull final Connection connection, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var result = this.connectionInteractor.updateConnection(connectionId, connection.convert(), sessionKey, username).convert();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteConnection(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.connectionInteractor.deleteConnection(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
