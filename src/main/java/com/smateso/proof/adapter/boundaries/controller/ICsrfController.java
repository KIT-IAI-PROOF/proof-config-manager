/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.boundaries.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.lang.NonNull;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CSRF Controller")
@RestController
@RequestMapping("/csrf")
public interface ICsrfController {

    @GetMapping
    CsrfToken csrf(@NonNull final CsrfToken csrfToken);

}