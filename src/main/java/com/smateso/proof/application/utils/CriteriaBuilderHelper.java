/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
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

    public static Path<?> getPath(@NonNull final Root<?> root, @NonNull final String columnPath) {
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
                                .map((final var item) -> switch (item.getOperator()) {
                                    case LIKE -> builder.like(getPath(root, item.getField()).as(String.class), "%" + item.getValue() + "%");
                                    case NOT_LIKE -> builder.notLike(getPath(root, item.getField()).as(String.class), "%" + item.getValue() + "%");
                                    case GT -> builder.greaterThan(getPath(root, item.getField()).as(String.class), item.getValue());
                                    case GTE -> builder.greaterThanOrEqualTo(getPath(root, item.getField()).as(String.class), item.getValue());
                                    case LT -> builder.lessThan(getPath(root, item.getField()).as(String.class), item.getValue());
                                    case LTE -> builder.lessThanOrEqualTo(getPath(root, item.getField()).as(String.class), item.getValue());
                                    default -> builder.equal(getPath(root, item.getField()), item.getValue());
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
                final var sort = by(request.getSort().stream().map((final com.smateso.proof.adapter.model.Sort requestSort) -> new Sort.Order(Sort.Direction.fromString(requestSort.getSort()), requestSort.getField())).toList());
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
