package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IEdgeInteractor;
import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.EdgeRepository;
import edu.kit.iai.webis.proofmodels.dao.EdgeDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.List;

import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createPageable;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createSpecification;
import static com.smateso.proof.application.utils.LogMessages.EDGE_DAO_NOT_FOUND_MESSAGE;
import static com.smateso.proof.application.utils.PropertyCopyHelper.copyEntityProperties;

@Slf4j
@Builder
@AllArgsConstructor
public class EdgeInteractor implements IEdgeInteractor {

    private final IMessageInteractor messageInteractor;
    private final EdgeRepository edgeRepository;

    @Override
    public Page<EdgeDao> searchEdges(@NonNull final Request request) {
        final Specification<EdgeDao> specification = createSpecification(request);
        final var pageable = createPageable(request);
        return this.edgeRepository.findAll(specification, pageable);
    }

    @Override
    public List<EdgeDao> getEdges() {
        return this.edgeRepository.findAll();
    }

    @Override
    public EdgeDao getEdge(@NonNull final String id) {
        final var result = this.edgeRepository.findById(id);
        if (result.isPresent()) return result.get();
        else throw new NotFoundException(EDGE_DAO_NOT_FOUND_MESSAGE.formatted(id));
    }

    @Override
    public EdgeDao createEdge(@NonNull final EdgeDao edgeDao, @NonNull final String sessionKey, @NonNull final String username) {
        return this.edgeRepository.save(edgeDao);
    }

    @Override
    public EdgeDao updateEdge(@NonNull final String id, @NonNull final EdgeDao updatingEdge, @NonNull final String sessionKey, @NonNull final String username) {
        final var existingEdge = this.getEdge(id);
        return this.edgeRepository.save(copyEntityProperties(updatingEdge, existingEdge, true));
    }

    @Override
    public void deleteEdge(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        this.edgeRepository.deleteById(id);
    }

}
