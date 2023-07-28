package com.rocket.birthday.service.oauth;

import com.rocket.birthday.api.auth.request.KakaoOAuthTokenRequest;
import com.rocket.birthday.api.auth.response.KakaoOAuthTokenView;
import com.rocket.birthday.config.auth.KakaoAuthConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "KakaoAuthFeignClient",
    url = "https://kauth.kakao.com",
    configuration = KakaoAuthConfiguration.class
)
public interface KakaoAuthClient {
  @PostMapping(
      value = "/oauth/token",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  KakaoOAuthTokenView getKakaoOAuthToken(@RequestBody KakaoOAuthTokenRequest body);

}
