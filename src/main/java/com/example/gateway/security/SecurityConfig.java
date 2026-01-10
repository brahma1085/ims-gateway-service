package com.example.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final String USER = "ROLE_USER";
    private static final String ADMIN = "ROLE_ADMIN";

    private final ReactiveJwtAuthenticationConverter jwtAuthenticationConverter;

    public SecurityConfig(ReactiveJwtAuthenticationConverter jwtAuthenticationConverter) {
        this.jwtAuthenticationConverter = jwtAuthenticationConverter;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(ex -> ex.pathMatchers("/actuator/**", "/swagger-ui/**", "/v3/api-docs/**", "/", "/index.html", "/favicon.ico", "/*.css", "/*.js", "/assets/**", "/browser/**")
                        .permitAll().pathMatchers("/api/inventory/**", "/api/admin/**").hasAuthority(ADMIN)
                        .pathMatchers("/api/orders/**", "/api/customers/**").hasAnyAuthority(ADMIN, USER).anyExchange()
                        .authenticated())
                .oauth2ResourceServer(
                        oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)))
                .build();
    }
}
