/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.config.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuration class for setting up WebSocket messaging in the application.
 * This class implements {@link WebSocketMessageBrokerConfigurer} to configure
 * and enable the WebSocket message broker and STOMP protocol for messaging.
 * It defines endpoints and message broker prefixes required for WebSocket communication.
 * <p>
 * Methods:
 * - {@link #configureMessageBroker(MessageBrokerRegistry)}: Configures the message broker
 * with application destination prefixes, and a simple message broker.
 * - {@link #registerStompEndpoints(StompEndpointRegistry)}: Registers STOMP endpoints
 * for WebSocket communication.
 */
@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    private final Environment environment;
    private final AuthenticationManager authenticationManager;

    @Value("${websocket.broker.relayHost}")
    private String relayHost;

    public WebsocketConfig(@NonNull final Environment environment, @NonNull final AuthenticationManager authenticationManager) {
        this.environment = environment;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Configures the message broker for WebSocket messaging. This method sets up the
     * application-specific destination prefixes for messages and enables a simple
     * broker for routing messages to specific destinations.
     *
     * @param registry the {@link MessageBrokerRegistry} used to configure message broker settings
     */
    @Override
    public void configureMessageBroker(@NonNull final MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        if (environment.matchesProfiles("prod", "dev")) {
            registry
                    .enableStompBrokerRelay("/topic", "/queue")
                    .setAutoStartup(true)
                    .setRelayHost(relayHost)
                    .setSystemLogin("admin")
                    .setSystemPasscode("admin")
                    .setClientLogin("admin")
                    .setClientPasscode("admin")
                    .setRelayPort(61613);
        }
    }

    /**
     * Registers STOMP endpoints for WebSocket communication. This method defines
     * the endpoints that clients can connect to for WebSocket messaging. It also
     * includes configurations for allowing cross-origin requests and enabling
     * SockJS as a fallback option for browsers that don't support WebSocket.
     *
     * @param registry the {@link StompEndpointRegistry} used to register STOMP endpoints
     */
    @Override
    public void registerStompEndpoints(@NonNull final StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*");
    }

    @Override
    public void configureClientInboundChannel(@NonNull final ChannelRegistration registration) {
        registration.interceptors(new SecurityInterceptor(this.authenticationManager));
    }

}
