package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IEdgeInteractor;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.EdgeRepository;
import edu.kit.iai.webis.proofmodels.dao.EdgeDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.smateso.proof.adapter.utils.NullAwareUtil.getNullPropertyNames;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.getPath;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;

public class EdgeInteractor implements IEdgeInteractor {

    private final EdgeRepository edgeRepository;

    public EdgeInteractor(@NonNull final EdgeRepository edgeRepository) {
        this.edgeRepository = edgeRepository;
    }

    @Override
    public Page<EdgeDao> searchEdges(@NonNull final Request request) {
        final Pageable pageable;
        if (request.getPagination() != null) {
            if (request.getSort() != null) {
                final var sort = by(request.getSort().stream().map((final Request.Sort requestSort) -> new Sort.Order(Sort.Direction.fromString(requestSort.getSort()), requestSort.getField())).toList());
                pageable = of(request.getPagination().getPage(), request.getPagination().getPageSize(), sort);
            } else {
                pageable = of(request.getPagination().getPage(), request.getPagination().getPageSize());
            }
        } else {
            pageable = Pageable.unpaged();
        }
        return this.edgeRepository.findAll((final Root<EdgeDao> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            final var filter = request.getFilter();
            if (filter != null) {
                final var items = filter.getItems();
                items.forEach((final Request.FilterItem item) -> predicates.add(builder.equal(getPath(root, item.getField()), item.getValue())));
            }
            return builder.and(predicates.toArray(new Predicate[]{}));
        }, pageable);
    }

    @Override
    public EdgeDao getEdge(@NonNull final String id) {
        final var result = this.edgeRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("EdgeDao with id %s not found".formatted(id));
        }
    }

    @Override
    public EdgeDao createEdge(@NonNull final EdgeDao edgeDao, @NonNull final String sessionKey, @NonNull final String username) {
        return this.edgeRepository.save(edgeDao);
    }

    @Override
    public EdgeDao updateEdge(@NonNull final String id, @NonNull final EdgeDao edgeDao, @NonNull final String sessionKey, @NonNull final String username) {
        final var existingEdge = this.getEdge(id);
        copyProperties(edgeDao, existingEdge, getNullPropertyNames(edgeDao));
        existingEdge.setPoints(existingEdge.getPoints());
        return this.edgeRepository.save(existingEdge);
    }

    @Override
    public void deleteEdge(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        this.edgeRepository.deleteById(id);
    }

    @Override
    public List<EdgeDao> getEdges() {
        return this.edgeRepository.findAll();
    }

}
