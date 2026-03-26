/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.config.messaging;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import static org.springframework.messaging.simp.stomp.StompCommand.CONNECT;

@Configuration
public class SecurityInterceptor implements ChannelInterceptor {

    public static final String ACCESS_TOKEN = "X-ACCESS_TOKEN";
    private final AuthenticationManager authenticationManager;

    public SecurityInterceptor(@NonNull final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Message<?> preSend(@NonNull final Message<?> message, @NonNull final MessageChannel channel) {
        final var accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor != null && CONNECT.equals(accessor.getCommand())) {
            String token = accessor.getFirstNativeHeader(ACCESS_TOKEN);
            JwtAuthenticationToken user = (JwtAuthenticationToken) authenticationManager.authenticate(new BearerTokenAuthenticationToken(token));
            accessor.setUser(user);
        }
        return message;
    }

}
