package com.smateso.proof.adapter.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND, reason = "No such entity")
public class NotFoundException extends RuntimeException {

    public NotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

}