package com.smateso.proof.application.utils;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

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

}
