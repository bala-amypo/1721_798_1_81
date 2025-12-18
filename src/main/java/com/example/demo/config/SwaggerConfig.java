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
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("https://9025.408procr.amypo.ai/");
        server.setDescription("Portal Server");

        return new OpenAPI()
                .info(new Info()
                        .title("API Documentation")
                        .version("1.0"))
                .servers(List.of(server));
    }
}
