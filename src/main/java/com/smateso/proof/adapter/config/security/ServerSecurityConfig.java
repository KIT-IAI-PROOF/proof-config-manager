package com.smateso.proof.adapter.config.security;

import com.smateso.proof.adapter.model.CorsProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.messaging.web.csrf.CsrfChannelInterceptor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager.Builder;

@Configuration
@EnableWebSecurity
@EnableWebSocketSecurity
@EnableConfigurationProperties(CorsProperties.class)
public class ServerSecurityConfig {

    public static final String REALM_ACCESS = "realm_access";
    public static final String ROLE = "ROLE_";
    public static final String ROLES = "roles";
    public static final String GROUPS = "groups";

    /**
     * Configure the server security filter chain.
     *
     * @param http Object to be configured.
     * @return Configured filterChain.
     * @throws Exception On failure to configure filter chain.
     */
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http, @Qualifier("corsProperties") final CorsProperties corsProperties) throws Exception {
        http
                .cors((final var cors) -> {
                    final var configuration = new CorsConfiguration();
                    final var source = new UrlBasedCorsConfigurationSource();
                    configuration.setAllowedOriginPatterns(corsProperties.getOrigins());
                    configuration.setAllowedMethods(corsProperties.getMethods());
                    configuration.setAllowedHeaders(corsProperties.getHeaders());
                    configuration.setAllowCredentials(true);
                    source.registerCorsConfiguration("/**", configuration);
                    cors.configurationSource(source);
                })
                .csrf((var csrf) -> {
                    csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
                    csrf.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler());
                })
                .authorizeHttpRequests((final var authorize) -> {
                    authorize.requestMatchers("/swagger-ui/**").permitAll();
                    authorize.requestMatchers("/v3/api-docs/**").permitAll();
                    authorize.anyRequest().permitAll();
                })
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer((final var oauth2) ->
                        oauth2.jwt((final var jwt) -> jwt
                                .jwtAuthenticationConverter((final var converter) -> {
                                    final var grantedAuthorities = new ArrayList<GrantedAuthority>();
                                    if (converter.hasClaim(REALM_ACCESS)) {
                                        final Map<String, Collection<String>> realmAccess = converter.getClaim(REALM_ACCESS);
                                        final Collection<String> roles = realmAccess.get(ROLES);
                                        roles.stream()
                                                .map((final var role) -> new SimpleGrantedAuthority(ROLE + role))
                                                .forEach(grantedAuthorities::add);
                                    }
                                    if (converter.hasClaim(GROUPS)) {
                                        final Collection<String> groups = converter.getClaim(GROUPS);
                                        groups.stream()
                                                .map((final var role) -> new SimpleGrantedAuthority(ROLE + role.replace("/", "")))
                                                .forEach(grantedAuthorities::add);
                                    }
                                    return new JwtAuthenticationToken(converter, grantedAuthorities);
                                }))
                );
        return http.build();
    }

    @Bean
    AuthorizationManager<Message<?>> messageAuthorizationManager(final Builder messages) {
        messages.anyMessage().permitAll().build();
        return messages.build();
    }

    @Bean
    public BearerTokenResolver bearerTokenResolver() {
        final var resolver = new DefaultBearerTokenResolver();
        resolver.setAllowUriQueryParameter(true);
        return resolver;
    }

    @Bean
    public ChannelInterceptor csrfChannelInterceptor() {
        return new CsrfChannelInterceptor();
    }

}