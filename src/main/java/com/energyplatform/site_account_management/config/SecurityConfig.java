package com.energyplatform.site_account_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    private final Converter<Jwt,Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter;


    public SecurityConfig(Converter<Jwt,Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter) {

        this.jwtAuthenticationConverter =jwtAuthenticationConverter;
    }


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http

                // REST API → disable CSRF
                .csrf(
                        ServerHttpSecurity.CsrfSpec::disable
                )

                // Authorization rules
                .authorizeExchange(exchange ->

                        exchange

                                // Every endpoint requires JWT
                                .anyExchange()
                                .authenticated()
                )

                // JWT Resource Server
                .oauth2ResourceServer(oauth2 ->

                        oauth2.jwt(jwt ->

                                jwt.jwtAuthenticationConverter(
                                        jwtAuthenticationConverter
                                )
                        )
                )

                .build();
    }
}