package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        SecurityScheme bearerScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        Server productionServer = new Server()
                .url("http://9250.pro604cr.amypo.ai/")
                .description("Production Server");

        Server localServer = new Server()
                .url("http://localhost:9001")
                .description("Local Server");

        return new OpenAPI()
                .info(new Info()
                        .title("Digital Asset Lifecycle & Audit Trail API")
                        .description("Asset management with lifecycle, transfers, disposals and JWT security")
                        .version("1.0.0")
                )
                .servers(List.of(productionServer, localServer))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", bearerScheme)
                )
                .addSecurityItem(new SecurityRequirement()
                        .addList("bearerAuth"));
    }
}
