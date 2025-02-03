package com.application.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {

    @Value("${jwt.secret_key}")
    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }
}
