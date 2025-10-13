package com.app.wai.user_service.common;


import com.app.wai.user_service.config.AppProperties;
import com.app.wai.user_service.config.properties.JwtProperties;
import com.app.wai.user_service.model.dto.JwtTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Jwt Helper for any related to jwt.
 */
@Component
@RequiredArgsConstructor
public class JwtHelper {
    private final AppProperties appProperties;
    private JwtProperties jwtProperties;
    private SecretKey key;

    @PostConstruct
    public void init() {
        this.jwtProperties = appProperties.getJwt();
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(JwtTokenDto dto) {
        return Jwts.builder()
            .subject(dto.getUsername())
            .claim("userId",dto.getUserId())
            .claim("fullname",dto.getFullname())
            .issuedAt(new Date())
            .expiration(
                new Date((new Date()).getTime() + jwtProperties.getAccessTokenExpiryMillis()))
            .signWith(key)
            .compact();
    }

    public Optional<Claims> validateJwtToken(String token){
        try{
            JwtParser parse = Jwts.parser().verifyWith(key).build();
            Jws<Claims> test = parse.parseSignedClaims(token);

            return Optional.of(test.getPayload());
        }catch (Exception e){
            return Optional.empty();
        }
    }

    public String generateRefreshToken(JwtTokenDto dto) {
        return Jwts.builder()
            .subject(dto.getUsername())
            .claim("userId",dto.getUserId())
            .claim("fullname",dto.getFullname())
            .issuedAt(new Date())
            .expiration(
                new Date((new Date()).getTime() + jwtProperties.getRefreshTokenExpiryMillis()))
            .signWith(key)
            .compact();
    }


}
