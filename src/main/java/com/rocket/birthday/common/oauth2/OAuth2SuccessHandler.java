package com.rocket.birthday.common.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  private final TokenService tokenService;
  // private final UserRequestMapper userRequestMapper;
  private final ObjectMapper objectMapper;


  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authentication) throws IOException, ServletException {
    super.onAuthenticationSuccess( request, response, chain, authentication );

    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
    // MemberDto memberDto = userRequestMapper.toDto(oAuth2User);

    // access token, refresh token 생성 및 발급
    // token 을 포함한 리다이렉트 구현

  }
}
