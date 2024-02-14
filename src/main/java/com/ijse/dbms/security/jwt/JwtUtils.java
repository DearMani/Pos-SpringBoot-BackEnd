package com.ijse.dbms.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    
    @Value("${app.secret}")
    private String secret;

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateJwtToken(Authentication authentication, String userType) {
        System.out.println("*********************************"+userType);
        Map<String, Object> claims = new HashMap<>();
        claims.put("usertype", userType);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + 86400000))
            .signWith(key(), SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean validateToken(String jwtToken) {

        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(jwtToken);
            return true;
        } catch (MalformedJwtException e) {
            System.err.println("Token has been changed. Invalid");
        } catch(ExpiredJwtException e) {
            System.err.println("Token is expired");
        } catch(UnsupportedJwtException e) {
            System.err.println("Unsupported token");
        } catch(IllegalArgumentException e) {
            System.err.println("Blank token");
        }

        return false;

    }

   
    public String getUsernameFromToken(String jwtToken) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(jwtToken).getBody().getSubject();
    }

     private Claims extractAllClaims(String token) {

         return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
         final Claims claims = extractAllClaims(token);
         return  claimsResolver.apply(claims);
    }

    public String extractUserType(String token) {
        System.out.println("********************"+extractClaim(token, claims -> claims.get("usertype", String.class))+"??????");
        return extractClaim(token, claims -> claims.get("usertype", String.class));
    }

}
