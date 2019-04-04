package com.spoom.gear.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * package com.spoom.gear.config.security
 *
 * @author spoomlan
 * @date 2019-04-03
 */
@Component
public class JwtUtils {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private Long expiration;

  private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

  private String generateToken(String subject, Map<String, Object> claims) {
    return generate(subject, claims, expiration);
  }

  private String generateToken(String subject, Map<String, Object> claims, Long expiration) {
    return generate(subject, claims, expiration);
  }

  /**
   * get username from jwt token
   * @param token
   * @return
   */
  public String getUsernameFromToken(String token) {
    String username;
    try {
      final Claims claims = getClaimsFromToken(token);
      username = claims.getSubject();
    } catch (Exception e) {
      username = null;
    }
    return username;
  }

  public Date getCreatedDateFromToken(String token) {
    Date created;
    try {
      final Claims claims = getClaimsFromToken(token);
      created = claims.getIssuedAt();
    } catch (Exception e) {
      created = null;
    }
    return created;
  }

  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  private Date generateExpirationDate(long expiration) {
    return new Date(System.currentTimeMillis() + expiration * 1000);
  }

  private String generate(String subject, Map<String, Object> claims, Long expiration) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setId(UUID.randomUUID().toString())
        .setIssuedAt(new Date())
        .setExpiration(generateExpirationDate(expiration))
        .compressWith(CompressionCodecs.DEFLATE)
        .signWith(SIGNATURE_ALGORITHM, secret)
        .compact();
  }

  /**
   * get exipre time from token
   * @param token
   * @return
   */
  public Date getExpirationDateFromToken(String token) {
    Date expiration;
    try {
      final Claims claims = getClaimsFromToken(token);
      expiration = claims.getExpiration();
    } catch (Exception e) {
      expiration = null;
    }
    return expiration;
  }

  private Claims getClaimsFromToken(String token) {
    Claims claims;
    try {
      claims = Jwts.parser()
          .setSigningKey(secret)
          .parseClaimsJws(token)
          .getBody();
    } catch (Exception e) {
      claims = null;
    }
    return claims;
  }
}

