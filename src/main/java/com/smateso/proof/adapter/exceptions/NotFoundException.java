/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, code = HttpStatus.NOT_FOUND, reason = "Entity not found")
public class NotFoundException extends RuntimeException {

    public NotFoundException(@NonNull final String message) {
        super(message);
    }

    public NotFoundException(@NonNull final String message, @NonNull final Throwable cause) {
        super(message, cause);
    }

}
