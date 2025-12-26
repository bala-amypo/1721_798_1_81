package com.example.demo.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.*;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Digital Asset Lifecycle & Audit Trail API")
                        .version("1.0")
                        .description("Asset tracking, transfers, lifecycle & disposal"));
    }
}
