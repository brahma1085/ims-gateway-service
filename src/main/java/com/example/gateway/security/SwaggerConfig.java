package com.example.gateway.security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI gatewayOpenAPI() {
		return new OpenAPI().info(new Info().title("Microservices API Gateway")
				.description("Aggregated Swagger UI via Spring Cloud Gateway").version("1.0.0"));
	}

	@Bean
	public GroupedOpenApi inventoryApi() {
		return GroupedOpenApi.builder().group("Inventory Service").pathsToMatch("/api/inventory/**").build();
	}

	@Bean
	public GroupedOpenApi ordersApi() {
		return GroupedOpenApi.builder().group("Orders Service").pathsToMatch("/api/orders/**").build();
	}

	@Bean
	public GroupedOpenApi customersApi() {
		return GroupedOpenApi.builder().group("Customers Service").pathsToMatch("/api/customers/**").build();
	}
}