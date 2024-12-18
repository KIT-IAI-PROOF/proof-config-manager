package com.smateso.proof.adapter.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorProvider")
public class AuditorProvider implements AuditorAware<String> {

    public static final String PREFERRED_USERNAME = "preferred_username";

    public static String getUsername(@NonNull final Authentication authentication) {
        try {
            final var jwt = (Jwt) authentication.getPrincipal();
            return jwt.getClaimAsString(PREFERRED_USERNAME);
        } catch (final Exception e) {
            throw new RuntimeException("Could not get username from authentication", e);
        }
    }

    @NonNull
    @Override
    public Optional<String> getCurrentAuditor() {
        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) return Optional.empty();
        final var username = getUsername(authentication);
        return Optional.of(username);
    }

}