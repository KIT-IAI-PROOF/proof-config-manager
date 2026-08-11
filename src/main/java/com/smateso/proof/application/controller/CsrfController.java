/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.ICsrfController;
import org.springframework.lang.NonNull;
import org.springframework.security.web.csrf.CsrfToken;

public class CsrfController implements ICsrfController {

    @Override
    public CsrfToken csrf(@NonNull final CsrfToken csrfToken) {
        return csrfToken;
    }

}
