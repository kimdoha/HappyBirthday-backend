package com.rocket.birthday.api.auth.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaoOAuthTokenRequest {
  private String grant_type;
  private String client_id;
  private String redirect_uri;
  private String code;
}
