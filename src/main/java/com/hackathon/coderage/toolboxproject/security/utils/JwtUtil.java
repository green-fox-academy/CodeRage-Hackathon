package com.hackathon.coderage.toolboxproject.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

  // region Fields
  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String TOKEN_BEARER = "Bearer ";

  private static final String SECRET_KEY = "SuperSecureSuperSecretSuperKey";
  private static final long EXPIRATION_TIME = 3600000;
  // endregion Fields


  // region Extractions
  public String extractUsername(String token) {
    return this.extractClaim(token, Claims::getSubject);
  }

  private Date extractExpiration(String token) {
    return this.extractClaim(token, Claims::getExpiration);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    return claimsResolver.apply(this.extractAllClaims(token));
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
  }
  // endregion Extractions


  // region Validation
  private Boolean isTokenExpired(String token) {
    return this.extractExpiration(token).before(new Date());
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !this.isTokenExpired(token));
  }
  // endregion Validation


  // region Generation
  public String generateToken(UserDetails userDetails) {
    HashMap<String, Object> claims = new HashMap<>();
    String role = userDetails.getAuthorities().stream().findFirst().toString();
    claims.put("claim", role);
    return this.createToken(claims, userDetails.getUsername());
  }

  private String createToken(Map<String, Object> claims, String username) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(username)
        .setIssuedAt(new Timestamp(System.currentTimeMillis()))
        .setExpiration(new Timestamp(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }
  // endregion Generation
}
