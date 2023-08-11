package com.rocket.birthday.api.auth.response;

import com.rocket.birthday.api.auth.request.KakaoOAuthTokenRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoOAuthTokenView {
  private String token_type;
  private String access_token;
  private int expires_in;
  private String refresh_token;
  private int refresh_token_expires_in;
}
