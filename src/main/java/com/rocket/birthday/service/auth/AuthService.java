package com.rocket.birthday.service.auth;

import com.rocket.birthday.api.auth.request.KakaoOAuthTokenRequest;
import com.rocket.birthday.api.auth.response.KakaoOAuthTokenView;
import com.rocket.birthday.api.auth.response.KakaoUserInfoView;
import com.rocket.birthday.config.PropertiesConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

  private final KakaoAuthClient kakaoAuthClient;
  private final KakaoAPIClient kakaoAPIClient;
  private final PropertiesConfiguration propertiesConfiguration;
  private static final String BEARER = "Bearer ";
  private static final String PROPERTY_KEYS = "[\"kakao_account.profile\", \"kakao_account.name\", \"kakao_account.email\"]";

  public KakaoOAuthTokenView getKakaoOAuthToken(String code) {

    KakaoOAuthTokenRequest body = KakaoOAuthTokenRequest.builder()
        .grant_type(propertiesConfiguration.getGrant_type())
        .client_id(propertiesConfiguration.getClient_id())
        .redirect_uri(propertiesConfiguration.getRedirect_uri())
        .code(code)
        .build();

    return kakaoAuthClient.getKakaoOAuthToken(body);
  }

  public KakaoUserInfoView getKakaoUserInfo(String accessToken) {
    return kakaoAPIClient.getKakaoUserInfo( BEARER + accessToken, PROPERTY_KEYS);
  }
}
