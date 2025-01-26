package com.smateso.proof.application.utils;

import com.smateso.proof.adapter.model.Request;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;

public class CriteriaBuilderHelper {

    public static Path<?> getPath(Root<?> root, String columnPath) {
        if (!columnPath.contains(".")) {
            return root.get(columnPath);
        }
        String[] levels = columnPath.split("\\.");
        Path<?> path = root;
        for (String level : levels) {
            path = path.get(level);
        }
        return path;
    }

    public static <T> Specification<T> createSpecification(@NonNull final Request request) {
        return (final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) -> {
            final var filter = request.getFilter();
            if (filter != null && !filter.getItems().isEmpty()) {
                return builder
                        .and(filter
                                .getItems()
                                .stream()
                                .map((final var item) -> {
                                    return switch (item.getOperator()) {
                                        case "like" ->
                                                builder.like(getPath(root, item.getField()).as(String.class), "%" + item.getValue() + "%");
                                        case "notLike" ->
                                                builder.notLike(getPath(root, item.getField()).as(String.class), "%" + item.getValue() + "%");
                                        case "gt" ->
                                                builder.greaterThan(getPath(root, item.getField()).as(String.class), item.getValue());
                                        case "gte" ->
                                                builder.greaterThanOrEqualTo(getPath(root, item.getField()).as(String.class), item.getValue());
                                        default -> builder.equal(getPath(root, item.getField()), item.getValue());
                                    };
                                })
                                .toArray(Predicate[]::new));
            }
            return null;
        };
    }

    public static Pageable createPageable(@NonNull final Request request) {
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
        return pageable;
    }

}
