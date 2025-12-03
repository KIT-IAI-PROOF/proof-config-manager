package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IConnectionInteractor;
import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.ConnectionRepository;
import edu.kit.iai.webis.proofmodels.dao.ConnectionDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

import static com.smateso.proof.adapter.model.Action.*;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createPageable;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createSpecification;
import static com.smateso.proof.application.utils.LogMessages.CONNECTION_DAO_NOT_FOUND_MESSAGE;
import static com.smateso.proof.application.utils.PropertyCopyHelper.deepCopyEntityProperties;
import static java.util.Objects.requireNonNull;

@Slf4j
@Builder
@AllArgsConstructor
public class ConnectionInteractor implements IConnectionInteractor {

    private static final List<String> COMPLEX_FIELDS = List.of("points");
    private static final String ENTITY_CONNECTION = "connections";
    private final IMessageInteractor messageInteractor;
    private final ConnectionRepository connectionRepository;

    @Override
    public Page<ConnectionDao> searchConnections(@NonNull final Request request) {
        final Specification<ConnectionDao> specification = createSpecification(request);
        final var pageable = createPageable(request);
        return this.connectionRepository.findAll(specification, pageable);
    }

    @Override
    public List<ConnectionDao> getConnections() {
        return this.connectionRepository.findAll();
    }

    @Override
    public ConnectionDao getConnection(@NonNull final String id) {
        final var result = this.connectionRepository.findById(id);
        if (result.isPresent()) return result.get();
        else throw new NotFoundException(CONNECTION_DAO_NOT_FOUND_MESSAGE.formatted(id));
    }

    @Override
    public ConnectionDao createConnection(@NonNull final ConnectionDao connectionDao, @Nullable final String sessionKey, @NonNull final String username) {
        final var result = this.connectionRepository.save(connectionDao);
        this.messageInteractor.sendUpdate(ENTITY_CONNECTION, requireNonNull(result.getId()), CREATED, sessionKey, username);
        return result;
    }

    @Override
    public ConnectionDao updateConnection(@NonNull final String id, @NonNull final ConnectionDao partial, @Nullable final String sessionKey, @NonNull final String username) {
        final var optionalConnection = this.connectionRepository.findById(id);
        if (optionalConnection.isPresent()) {
            final var connection = optionalConnection.get();
            deepCopyEntityProperties(partial, connection, true, List.of(), COMPLEX_FIELDS);
            final var result = this.connectionRepository.save(connection);
            this.messageInteractor.sendUpdate(ENTITY_CONNECTION, requireNonNull(result.getId()), UPDATED, sessionKey, username);
            return result;
        } else {
            final var result = this.connectionRepository.save(partial);
            this.messageInteractor.sendUpdate(ENTITY_CONNECTION, requireNonNull(result.getId()), UPDATED, sessionKey, username);
            return result;
        }
    }

    @Override
    public void deleteConnection(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username) {
        this.connectionRepository.deleteById(id);
        this.messageInteractor.sendUpdate(ENTITY_CONNECTION, id, DELETED, sessionKey, username);
    }

}
