/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.model;

public enum EOperator {
    LIKE("like"),
    NOT_LIKE("notLike"),
    IS("is"),
    GT("gt"),
    GTE("gte"),
    LT("lt"),
    LTE("lte");

    EOperator(final String like) {
    }
}
