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

import java.util.List;

import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createPageable;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createSpecification;
import static com.smateso.proof.application.utils.LogMessages.CONNECTION_DAO_NOT_FOUND_MESSAGE;
import static com.smateso.proof.application.utils.PropertyCopyHelper.relationalCopyEntityProperties;

@Slf4j
@Builder
@AllArgsConstructor
public class ConnectionInteractor implements IConnectionInteractor {

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
    public ConnectionDao createConnection(@NonNull final ConnectionDao edgeDao, @NonNull final String sessionKey, @NonNull final String username) {
        return this.connectionRepository.save(edgeDao);
    }

    @Override
    public ConnectionDao updateConnection(@NonNull final String id, @NonNull final ConnectionDao updatingEdge, @NonNull final String sessionKey, @NonNull final String username) {
        final var existingEdge = this.getConnection(id);
        return this.connectionRepository.save(relationalCopyEntityProperties(updatingEdge, existingEdge, true, List.of()));
    }

    @Override
    public void deleteConnection(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        this.connectionRepository.deleteById(id);
    }

}
