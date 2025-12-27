package com.example.gateway.security;

import java.util.UUID;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

@Configuration
public class RequestIdFilter {

	@Bean
	public GlobalFilter requestIdGlobalFilter() {
		return (exchange, chain) -> {
			ServerWebExchange mutatedExchange = exchange.mutate()
					.request(r -> r.header("X-Request-Id", UUID.randomUUID().toString())).build();

			return chain.filter(mutatedExchange);
		};
	}
}
