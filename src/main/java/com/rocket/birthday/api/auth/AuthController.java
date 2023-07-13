package com.rocket.birthday.api.auth;

import com.rocket.birthday.api.auth.request.KakaoAuthorizationCodeRequest;
import com.rocket.birthday.api.auth.request.KakaoOAuthTokenRequest;
import com.rocket.birthday.api.auth.response.KakaoOAuthTokenView;
import com.rocket.birthday.config.PropertiesConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

  private final AuthClient authClient;
  private final PropertiesConfiguration propertiesConfiguration;

  // TODO KAKAO 인증 코드를 통한 OAuth Token 발급
  @PostMapping("/kakao/token")
  public KakaoOAuthTokenView getKaKaoAuthorizationToken(
      @RequestBody KakaoAuthorizationCodeRequest request
  ) {

    KakaoOAuthTokenRequest body = KakaoOAuthTokenRequest.builder()
        .grant_type(propertiesConfiguration.getGrant_type())
        .client_id(propertiesConfiguration.getClient_id())
        .redirect_uri(propertiesConfiguration.getRedirect_uri())
        .code(request.getCode())
        .build();

    return authClient.getKakaoOAuthToken(body);
  }
}