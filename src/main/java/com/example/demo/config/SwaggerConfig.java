package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Digital Asset Lifecycle API")
                        .version("1.0")
                        .description("API documentation for Digital Asset Lifecycle system"))
                .servers(List.of(
                        new Server()
                                .url("https://9250.pro604cr.amypo.ai/")
                                .description("Local Development Server")
                ));
    }
}
