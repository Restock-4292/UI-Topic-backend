package com.restock.platform.shared.infrastructure.documentation.openapi.configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI restockPlatformOpenApi() {
        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8080"));
    }
}




