package com.lvv.users_manager.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import com.lvv.users_manager.models.jwtinfo.LoggedUserJWTInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTUtils {

    public static final String EMAIL_CLAIM = "email";
    public static final String USERNAME_CLAIM = "username";
    public static final String ROLES_CLAIM = "roles";
    public static final String PERMISSIONS_CLAIM = "permissions";
    public static final String APPLICATION_CLAIM = "application";

    private JWTUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String generateToken (LoggedUserJWTInfo loggedUserJWTInfo, String encodedKey) {
        SecretKey secret = Keys.hmacShaKeyFor(encodedKey.getBytes());
        String subject = loggedUserJWTInfo.username() != null ? loggedUserJWTInfo.username() : loggedUserJWTInfo.email();

        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        Date issuedAt = Date.from(now.toInstant());
        Date expiration = Date.from(now.plusHours(1).toInstant());

        return Jwts.builder()
            .subject(subject)
            .issuedAt(issuedAt)
            .expiration(expiration)
            .signWith(secret)
            .claims(generateClaimsForJWT(loggedUserJWTInfo))
            .compact();
    }

    private static Map<String, Object> generateClaimsForJWT( LoggedUserJWTInfo loggedUserJWTInfo ) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(EMAIL_CLAIM, loggedUserJWTInfo.email());
        claims.put(USERNAME_CLAIM, loggedUserJWTInfo.username());
        claims.put(ROLES_CLAIM, loggedUserJWTInfo.roles());
        claims.put(PERMISSIONS_CLAIM, loggedUserJWTInfo.permissions());
        claims.put(APPLICATION_CLAIM, loggedUserJWTInfo.applicationUUID());
        return claims;
    }

    public static Claims getClaims(String token, String encodedKey) {
        SecretKey secret = Keys.hmacShaKeyFor(encodedKey.getBytes());
        return Jwts.parser()
            .verifyWith(secret)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

}