package com.shopsphere.security;

import com.nimbusds.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final long expirationMs;

    public JwtService(JwtEncoder jwtEncoder,@Value("${app.jwt.expiration}") long expirationMs){
        this.jwtEncoder = jwtEncoder;
        this.expirationMs = expirationMs;
    }

    public String generateToken(String username, Collection<? extends GrantedAuthority> authorities){
     Instant now  = Instant.now();
     Instant expiry = now.plusMillis(expirationMs);

     List<String> authorityList = authorities.stream().map(GrantedAuthority::getAuthority).toList();

        JwtClaimsSet claims = JwtClaimsSet.builder().issuer("shopsphere")
                .subject(username)
                .issuedAt(now)
                .expiresAt(expiry)
                .claim("authorities",authorityList)
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256)
                .type("JWT")
                .build();
        Jwt jwt = jwtEncoder.encode(JwtEncoderParameters.from(header,claims));
        return jwt.getTokenValue();
    }

}
