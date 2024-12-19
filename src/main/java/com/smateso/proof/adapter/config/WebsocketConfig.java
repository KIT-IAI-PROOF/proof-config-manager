package com.smateso.proof.adapter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
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
 * for WebSocket communication, including SockJS fallback options.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Configures the message broker for WebSocket messaging. This method sets up the
     * application-specific destination prefixes for messages and enables a simple
     * broker for routing messages to specific destinations.
     *
     * @param config the {@link MessageBrokerRegistry} used to configure message broker settings
     */
    @Override
    public void configureMessageBroker(@NonNull final MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app");
        config.enableSimpleBroker("/updates");
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
        registry
                .addEndpoint("/ws")
                .setAllowedOriginPatterns("http://localhost:3000");
    }

}
