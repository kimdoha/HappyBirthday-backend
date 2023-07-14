package com.rocket.birthday.api.auth;

import com.rocket.birthday.api.auth.request.KakaoAuthorizationCodeRequest;
import com.rocket.birthday.api.auth.request.KakaoUserInfoRequest;
import com.rocket.birthday.api.auth.response.KakaoOAuthTokenView;
import com.rocket.birthday.api.auth.response.KakaoUserInfoView;
import com.rocket.birthday.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

  private final AuthService authService;

  // TODO KAKAO 인증 코드를 통한 OAuth Token 발급
  //  [{"error":"invalid_grant","error_description":"authorization code not found for
  @PostMapping("/kakao/token")
  public KakaoOAuthTokenView getKaKaoAuthorizationToken(
      @RequestBody KakaoAuthorizationCodeRequest request
  ) {
    return authService.getKakaoOAuthToken(request.getCode());
  }

  // TODO Example
  // ERROR [{"msg":"this access token is already expired","code":-401}]
  @PostMapping("/kakao/info")
  public KakaoUserInfoView getKakaoUserInfo(@RequestBody KakaoUserInfoRequest request) {
    return authService.getKakaoUserInfo(request.getAccessToken());
  }
}