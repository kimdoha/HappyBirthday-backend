package com.rocket.birthday.service.jwt;

import static com.rocket.birthday.common.constant.BirthdayConstants.AUTHORIZATION_HEADER;
import static com.rocket.birthday.common.constant.BirthdayConstants.BEARER;

import com.rocket.birthday.config.jwt.JwtPropertiesConfiguration;
import com.rocket.birthday.service.member.dto.MemberDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

  private final JwtPropertiesConfiguration propertiesConfiguration;

  public String generateToken(Long memberId, String memberName) {
    Date now = new Date();
    Date expireDate = new Date( now.getTime() + propertiesConfiguration.getExpireTime() );

    return Jwts.builder()
        .setSubject(String.valueOf(memberId))
        .claim("memberName", memberName)
        .setIssuedAt(now)
        .signWith(SignatureAlgorithm.HS256, propertiesConfiguration.getSecretKey().getBytes())
        .setExpiration(expireDate)
        .compact();
  }

  public UsernamePasswordAuthenticationToken getAuthentication(String token) {
    Claims claims = getClaims( token );
    Long memberId = Long.valueOf(claims.getSubject());

    MemberDetails principal = new MemberDetails( memberId );
    return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
  }

  public Claims getClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(propertiesConfiguration.getSecretKey().getBytes())
        .build()
        .parseClaimsJws(token)
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
      Jwts.parserBuilder()
          .setSigningKey(propertiesConfiguration.getSecretKey().getBytes())
          .build()
          .parseClaimsJws(token);

      return true;
    } catch (JwtException exception) {
      // TODO error 추후에 수정
      log.info("JWT 오류입니다.");
    }

    return false;
  }
}
