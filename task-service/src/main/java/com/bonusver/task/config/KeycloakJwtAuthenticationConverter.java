package com.bonusver.task.config;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, JwtAuthenticationToken> {
    @Override
    public JwtAuthenticationToken convert(Jwt jwt) {
        Collection<String> roles =extractRoles(jwt, "realm_access");
        if (roles.isEmpty()) {
            roles = extractRoles(jwt, "resource_access.account");
        }
        Collection<GrantedAuthority> authorities = roles.stream()
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        String principalName = jwt.getClaimAsString("email");
        if (principalName == null || principalName.isEmpty()) {
            principalName = jwt.getClaimAsString("preferred_username");
        }

        return new JwtAuthenticationToken(jwt, authorities, principalName);
    }

    private Collection<String> extractRoles(Jwt jwt, String claimPath) {
        Object claims = jwt.getClaim(claimPath);
        if (claims instanceof Map) {
            Object roles = ((Map<?,?>) claims).get("roles");
            if (roles instanceof Collection) {
                return (Collection<String>) roles;
            }
        }
        return Collections.emptyList();
    }
/*
    @Override
    public JwtAuthenticationToken convert(Jwt jwt) {
        Map<String, Object> claims = jwt.getClaim("realm_access");
        Collection<String> roles = (Collection<String>) claims.getOrDefault("roles", Collections.emptyList());

        Collection<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new JwtAuthenticationToken(jwt, authorities);
    }*/


}
