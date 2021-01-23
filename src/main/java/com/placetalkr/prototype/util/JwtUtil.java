package com.placetalkr.prototype.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtUtil {

    private static final long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(15);
    private static final String secret = "pvtkey";

    private JwtUtil() { /* Util call not to be instantiated */}

    /**
     * Generate token using secret with expiration time and compression
     * @param subject
     * @return token string
     */
    public static String generateToken(String subject) {
        String jwtToken = Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return jwtToken;
    }

    /**
     * Validate token
     * @param token
     * @param username
     * @return boolean value
     */
    public static Boolean validateToken(String token, String username) {
        final String user = getUsernameFromToken(token);
        return (username.equals(user) && !isTokenExpired(token));
    }

    public static String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    private static Date getExpirationDateFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
    }
    private static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

}
