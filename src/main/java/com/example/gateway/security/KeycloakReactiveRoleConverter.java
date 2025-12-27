package com.example.gateway.security;

import java.util.List;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component
public class KeycloakReactiveRoleConverter implements Converter<Jwt, Flux<GrantedAuthority>> {

	@Override
	public Flux<GrantedAuthority> convert(Jwt jwt) {
		Map<String, Object> realmAccess = jwt.getClaim("realm_access");
		if (realmAccess == null || realmAccess.get("roles") == null) {
			return Flux.empty();
		}

		@SuppressWarnings("unchecked")
		List<String> roles = (List<String>) realmAccess.get("roles");

		return Flux.fromIterable(roles).map(role -> new SimpleGrantedAuthority("ROLE_" + role));
	}
}
