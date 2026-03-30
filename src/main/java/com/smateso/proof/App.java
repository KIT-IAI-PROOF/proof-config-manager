/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.boot.SpringApplication.run;

/**
 * The App class serves as the entry point for the PROOF Data Service app.
 * <p>
 * This class is annotated with several Spring annotations to enable key functionalities:
 * - @SpringBootApplication: Marks this as a Spring Boot app.
 * - @EnableCaching: Enables caching support within the app.
 * - @EntityScan: Configures the base packages for scanning JPA entities.
 * <p>
 * Also, the class is annotated with OpenAPI definitions to give API documentation:
 * - @OpenAPIDefinition: Specifies API metadata including title, description, version and contact information.
 * - @Server: Defines the server URL and description for the API.
 * <p>
 * Security configurations are defined with:
 * - @SecurityScheme: Configures OAuth2 with a client credentials flow and associated token URL.
 * <p>
 * The main method is the entry point of the application and is responsible for launching the Spring Boot application.
 */
@SpringBootApplication
@EntityScan("edu.kit.iai.webis.proofmodels")
@ComponentScan(basePackages = {"edu.kit.iai.webis.proofmodels", "com.smateso.proof"})
@OpenAPIDefinition(
        info = @Info(
                title = "PROOF Config Manager",
                description = "PROOF Config Manager",
                version = "1.0.0",
                contact = @Contact(name = "Smateso GmbH")
        ),
        servers = @Server(
                description = "Local",
                url = "http://localhost:8100")
)
@SecurityScheme(
        name = "oauth2",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                clientCredentials = @OAuthFlow(
                        tokenUrl = "${spring.security.oauth2.client.provider.keycloak.token-uri}",
                        scopes = {@OAuthScope(name = "openid", description = "openid scope")})))
public class App {

    public static void main(final String... args) {
        run(App.class, args);
    }

}
