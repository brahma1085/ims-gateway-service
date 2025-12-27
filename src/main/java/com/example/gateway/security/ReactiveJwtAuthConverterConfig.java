package com.example.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;

@Configuration
public class ReactiveJwtAuthConverterConfig {

	@Bean
	public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter(KeycloakReactiveRoleConverter roleConverter) {
		ReactiveJwtAuthenticationConverter converter = new ReactiveJwtAuthenticationConverter();
		converter.setJwtGrantedAuthoritiesConverter(roleConverter);
		return converter;
	}
}
