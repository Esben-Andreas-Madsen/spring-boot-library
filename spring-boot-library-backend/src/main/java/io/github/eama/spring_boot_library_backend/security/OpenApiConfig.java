package io.github.eama.spring_boot_library_backend.security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("oauth2",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .flows(new OAuthFlows()
                                                .authorizationCode(
                                                        new OAuthFlow()
                                                                .authorizationUrl("http://host.docker.internal:1852/realms/library/protocol/openid-connect/auth")
                                                                .tokenUrl("http://host.docker.internal:1852/realms/library/protocol/openid-connect/token")
                                                )
                                        )
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("oauth2"));
    }
}