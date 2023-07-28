package com.rocket.birthday.api.auth;

import com.rocket.birthday.api.auth.request.KakaoAuthorizationCodeRequest;
import com.rocket.birthday.api.auth.request.KakaoUserInfoRequest;
import com.rocket.birthday.api.auth.response.KakaoOAuthTokenView;
import com.rocket.birthday.api.auth.response.KakaoUserInfoView;
import com.rocket.birthday.api.auth.response.MemberTokenView;
import com.rocket.birthday.service.oauth.AuthService;
import com.rocket.birthday.service.member.MemberService;
import jakarta.validation.Valid;
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
  private final MemberService memberService;

  @PostMapping("/kakao/token")
  public KakaoOAuthTokenView getKaKaoAuthorizationToken(
      @RequestBody @Valid KakaoAuthorizationCodeRequest request
  ) {
    return authService.getKakaoOAuthToken(request.getCode());
  }

  // TODO Example
  // ERROR [{"msg":"this access token is already expired","code":-401}]
  @PostMapping("/kakao/info")
  public KakaoUserInfoView getKakaoUserInfo(@RequestBody KakaoUserInfoRequest request) {
    return authService.getKakaoUserInfo(request.getAccessToken());
  }
  // TODO JwtAuthenticationEntryPoint  : 인증되지 않은 요청입니다.
  @PostMapping("/sign-in")
  public MemberTokenView signIn(@Valid @RequestBody KakaoAuthorizationCodeRequest request) {
    return authService.signInMember(request.getCode());
  }
}