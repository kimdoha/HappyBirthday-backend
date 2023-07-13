package com.rocket.birthday.service.auth;

import com.rocket.birthday.api.auth.response.KakaoUserInfoView;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "KakaoAPIFeignClient", url="https://kapi.kakao.com")
public interface KakaoAPIClient {
  @GetMapping(
      value = "/v2/user/me",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  KakaoUserInfoView getKakaoUserInfo(@RequestHeader("Authorization") String token);

}
