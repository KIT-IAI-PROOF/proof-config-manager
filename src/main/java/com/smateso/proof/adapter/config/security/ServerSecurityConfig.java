package com.smateso.proof.adapter.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ServerSecurityConfig {

    public static final String REALM_ACCESS = "realm_access";
    public static final String ROLE = "ROLE_";
    public static final String ROLES = "roles";
    public static final String GROUPS = "groups";

    /**
     * Configure the server security filter chain.
     * First enable CORS for localhost clients.
     *
     * @param http Object to be configured.
     * @return Configured filterChain.
     * @throws Exception On failure to configure filter chain.
     */
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http
                .cors((final var cors) -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOriginPatterns(List.of("http://localhost:3000"));
                    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
                    configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
                    configuration.setAllowCredentials(true);
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", configuration);
                    cors.configurationSource(source);
                })
                .csrf((final var csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests((final var authorize) -> authorize.requestMatchers("/ws/**").permitAll().anyRequest().authenticated())
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer((final var oauth2) ->
                        oauth2.jwt((final var jwt) -> jwt
                                .jwtAuthenticationConverter((final var converter) -> {
                                    final var grantedAuthorities = new ArrayList<GrantedAuthority>();
                                    // Evaluate roles
                                    if (converter.hasClaim(REALM_ACCESS)) {
                                        final Map<String, Collection<String>> realmAccess = converter.getClaim(REALM_ACCESS);
                                        final Collection<String> roles = realmAccess.get(ROLES);
                                        roles.stream()
                                                .map((final var role) -> new SimpleGrantedAuthority(ROLE + role))
                                                .forEach(grantedAuthorities::add);
                                    }
                                    // Evaluate group memberships
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

}