package com.rocket.birthday.service.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
  private final JwtTokenProvider jwtTokenProvider;
  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

      String token = jwtTokenProvider.resolveToken( request );

      try {
        if (StringUtils.hasText( token ) && jwtTokenProvider.validateToken( token )) {
          UsernamePasswordAuthenticationToken auth = jwtTokenProvider.getAuthentication( token );
          SecurityContextHolder.getContext().setAuthentication( auth );
        }
      } catch (Exception e) {
        // TODO exception 추후 수정
        log.info( "jwt filter exception : " + e.getMessage() );
        SecurityContextHolder.clearContext();

        return;
      }

      filterChain.doFilter( request, response );
  }
}
