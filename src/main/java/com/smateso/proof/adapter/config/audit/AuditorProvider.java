/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.config.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorProvider")
public class AuditorProvider implements AuditorAware<String> {

    public static final String PREFERRED_USERNAME = "preferred_username";

    public static String getUsername(@Nullable final Authentication authentication) {
        try {
            if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
                return jwt.getClaimAsString(PREFERRED_USERNAME);
            } else {
                return "anonymous";
            }
        } catch (final Exception e) {
            throw new RuntimeException("Could not get username from authentication", e);
        }
    }

    @NonNull
    @Override
    public Optional<String> getCurrentAuditor() {
        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        final var username = getUsername(authentication);
        return Optional.of(username);
    }

}