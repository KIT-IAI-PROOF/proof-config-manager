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

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Base",
                description = "Spring Boot Base",
                version = "1.0.0",
                contact = @Contact(name = "Smateso GmbH")
        ),
        servers = @Server(
                description = "Local",
                url = "http://localhost:8080")
)
@SecurityScheme(name = "oauth2", type = SecuritySchemeType.OAUTH2, flows = @OAuthFlows(clientCredentials = @OAuthFlow(tokenUrl = "${spring.security.oauth2.client.provider.keycloak.token-uri}", scopes = {@OAuthScope(name = "openid", description = "openid scope")})))
public class App {

    public static void main(final String... args) {
        run(App.class, args);
    }

}
