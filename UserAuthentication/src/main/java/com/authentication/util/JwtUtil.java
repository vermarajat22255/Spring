package com.authentication.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JwtUtil implements Serializable {
    public static final long JWT_TOKEN_VALIDITY = 5*60*60;
    @Value("${jwt.secret}")
    private String secret;
    private static final long serialVersionUID = -2550185165626007488L;

    // Generic claims method

    public <T> T getClaimsFromToken(String token, Function<Claims, T> claimResolvers){
        final Claims claims = getAllClaimsFromToken(token);
        return claimResolvers.apply(claims);
    }
    public Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // Get details from token

    public String getUserNameFromToken(String token){
        return getClaimsFromToken(token, claims -> claims.getSubject());
    }
    public Date getExpirationDateFromToken(String token){
        return getClaimsFromToken(token, claims -> claims.getExpiration());
    }

    // Token utility

    public Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    public boolean validateToken(String token, UserDetails user){
        return user.getUsername().equals(getUserNameFromToken(token)) && !isTokenExpired(token);
    }

    public String generateToken(UserDetails userDetails){
        HashMap<String, Object> claims = new HashMap<>();
        return doGenerateTokens(claims, userDetails.getUsername());
    }
    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string

    public String doGenerateTokens(HashMap<String, Object> claims, String username){
        String encodedString = Base64.getEncoder().encodeToString(secret.getBytes());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}