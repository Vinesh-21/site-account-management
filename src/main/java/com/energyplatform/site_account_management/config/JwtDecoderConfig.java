package com.energyplatform.site_account_management.config;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;
@Configuration
public class JwtDecoderConfig {
    @Bean
    public ReactiveJwtDecoder jwtDecoder(@Value("${jwt.secret}") String secret) {

        SecretKey key =
                Keys.hmacShaKeyFor(
                        secret.getBytes()
                );

        return NimbusReactiveJwtDecoder
                .withSecretKey(key)
                .macAlgorithm(
                        MacAlgorithm.HS512
                )
                .build();
    }
}
