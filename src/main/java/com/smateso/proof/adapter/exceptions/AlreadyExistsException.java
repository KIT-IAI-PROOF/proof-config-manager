/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, code = HttpStatus.CONFLICT, reason = "Entity with property already exists")
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(@NonNull final String message) {
        super(message);
    }

    public AlreadyExistsException(@NonNull final String message, @NonNull final Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistsException(@NonNull final Throwable cause) {
        super(cause);
    }

}
