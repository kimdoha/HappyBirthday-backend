package com.rocket.birthday.api.auth.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoOAuthTokenRequest {
  private String grant_type;
  private String client_id;
  private String redirect_uri;
  private String code;

  public static KakaoOAuthTokenRequest of(
      String grantType,
      String clientId,
      String redirectUri,
      String code
  ) {
    return KakaoOAuthTokenRequest.builder()
        .grant_type(grantType)
        .client_id(clientId)
        .redirect_uri(redirectUri)
        .code(code)
        .build();
  }
}
