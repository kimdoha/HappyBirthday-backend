package com.rocket.birthday.api.jwt;

import com.rocket.birthday.service.member.dtos.MemberDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class JwtTokenProvider {
  private static final String BEARER = "Bearer ";
  private static final String AUTHORIZATION_HEADER = "Authorization";
  private String jwt_secret_key;
  private long expire_time;

  public String generateToken(Long memberId, String memberName) {

    Date now = new Date();
    Date expireDate = new Date(now.getTime() + expire_time);

    return Jwts.builder()
        .setSubject(memberName)
        .claim("memberId", memberId)
        .setIssuedAt(now)
        .signWith(SignatureAlgorithm.HS256, jwt_secret_key)
        .setExpiration(expireDate)
        .compact();
  }

  public Authentication getAuthentication(String token) {
    Claims claims = getClaims(token);
    Long memberId = (Long) claims.get("memberId");
    MemberDetails principal = new MemberDetails(memberId);

    return new UsernamePasswordAuthenticationToken(principal, token);

  }

  public Claims getClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(jwt_secret_key)
        .build()
        .parseClaimsJwt(token)
        .getBody();
  }

  public String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

    if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)){
      return bearerToken.substring(BEARER.length());
    }

    return null;
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey( jwt_secret_key ).build().parseClaimsJwt( token );
      return true;
    } catch (JwtException exception) {
      // error 추후에 수정
      log.info("JWT 오류입니다.");
    }

    return false;
  }
}
