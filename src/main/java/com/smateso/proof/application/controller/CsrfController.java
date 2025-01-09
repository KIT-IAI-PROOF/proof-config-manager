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
