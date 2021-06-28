package com.cristianbalta.cloudmanagerworker.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtUtil {

    private final KeyPair keyPair;
    private static KeyPair staticKeyPair;

    public JwtUtil(KeyPair keyPair) {
        this.keyPair = keyPair;
        staticKeyPair = keyPair;
    }

    private static Claims getJwtClaims(String jwt) throws Exception {
        try {
            return Jwts.parserBuilder().setSigningKey(staticKeyPair.getPrivate()).build().parseClaimsJws(jwt).getBody();
        } catch (ExpiredJwtException ex) {
            throw new Exception("BAD_TOKEN");
        }
    }

    public String generateToken(String username) {

        return Jwts.builder().setSubject(username)
                .signWith(SignatureAlgorithm.RS256, keyPair.getPrivate())
                .setExpiration(Date.from(Instant.now().plus(10, ChronoUnit.HOURS)))
                .compact();
    }

    public static String extractUsername(String jwt) throws Exception {
        return getJwtClaims(jwt).getSubject();
    }

    public static String extractUsernameFromHeader(String requestHeader) throws Exception {
        return JwtUtil.extractUsername(requestHeader.split(" ")[1]);
    }


    private static boolean jwtIsExpired(String token) throws Exception {
        return getJwtClaims(token).getExpiration().before(new Date());

    }

    public static void validateToken(String token) throws Exception {
        if (jwtIsExpired(token)) {
            throw new Exception("TOKEN_EXPIRED");
        }
    }
}
