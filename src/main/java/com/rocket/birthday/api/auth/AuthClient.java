package com.rocket.birthday.api.auth;

import com.rocket.birthday.api.auth.request.KakaoOAuthTokenRequest;
import com.rocket.birthday.api.auth.response.KakaoOAuthTokenView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "kakaoFeignClient", url = "https://kauth.kakao.com")
public interface AuthClient {
  @PostMapping(
      value = "/oauth/token",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  KakaoOAuthTokenView getKakaoOAuthToken(@RequestBody KakaoOAuthTokenRequest body);
}
