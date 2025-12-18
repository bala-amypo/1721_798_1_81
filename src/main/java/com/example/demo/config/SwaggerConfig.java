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

        Server server = new Server();
        server.setUrl("https://9025.408procr.amypo.ai/");
        server.setDescription("Digital Asset Lifecycle Server");

        return new OpenAPI()
                .info(new Info()
                        .title("Digital Asset Lifecycle API")
                        .description("API for managing the complete lifecycle of digital assets")
                        .version("1.0"))
                .servers(List.of(server));
    }
}
