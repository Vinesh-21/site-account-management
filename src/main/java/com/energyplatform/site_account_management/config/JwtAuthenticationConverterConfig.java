package com.energyplatform.site_account_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

//This file converts a validated JWT into a Spring Security Authentication object.
//It extracts both the user's role and permissions from the JWT and converts them into Spring Security authorities for authorization.
@Configuration
public class JwtAuthenticationConverterConfig {

    @Bean
    public Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter() {

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {

            List<GrantedAuthority> authorities = new ArrayList<>();

            // Get permissions
            List<String> permissions = jwt.getClaimAsStringList("permissions");

            if (permissions != null) {

                authorities.addAll(
                        permissions.stream()
                                .map(SimpleGrantedAuthority::new)
                                .toList()
                );
            }

            // Get role
            String role =
                    jwt.getClaimAsString("role");

            if (role != null) {

                authorities.add(
                        new SimpleGrantedAuthority(
                                "ROLE_" + role
                        )
                );
            }

            return authorities;
        });

        return new ReactiveJwtAuthenticationConverterAdapter(
                converter
        );
    }
}