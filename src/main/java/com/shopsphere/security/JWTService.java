package com.shopsphere.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTService {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration}")
    private long expirationMs;

    public String getSecret() {
        return secret;
    }

    public long getExpirationMs() {
        return expirationMs;
    }

}
