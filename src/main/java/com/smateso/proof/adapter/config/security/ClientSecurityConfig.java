package com.smateso.proof.adapter.config.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.web.reactive.function.client.WebClient.builder;

@Getter
@Configuration
public class ClientSecurityConfig {

    /**
     * Configure oauth2 providers.
     *
     * @param token_uri     The token URI from the config.
     * @param client_id     Client ID URI from the config.
     * @param client_secret Client Secret from the config.
     * @return Configured registration repository.
     */
    @Bean
    @Profile(value = {"prod", "dev"})
    public ReactiveClientRegistrationRepository getRegistration(
            @Value("${spring.security.oauth2.client.provider.keycloak.token-uri}") @NonNull final String token_uri,
            @Value("${spring.security.oauth2.client.registration.keycloak.client-id}") @NonNull final String client_id,
            @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}") @NonNull final String client_secret) {
        final var registration = ClientRegistration
                .withRegistrationId("keycloak")
                .tokenUri(token_uri)
                .clientId(client_id)
                .clientSecret(client_secret)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();
        return new InMemoryReactiveClientRegistrationRepository(registration);
    }

    /**
     * Configure client for ArcGIS communication.
     * This is a client without oauth2 token.
     *
     * @return Configured WebClient.
     */
    @Bean(name = "defaultWebClient", value = "defaultWebClient")
    public WebClient defaultWebClient() {
        final var builder = builder();
        return builder.build();
    }

}
