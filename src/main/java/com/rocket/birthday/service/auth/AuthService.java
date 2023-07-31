package com.rocket.birthday.service.auth;

import static com.rocket.birthday.common.constant.BirthdayConstants.BEARER;
import static com.rocket.birthday.common.constant.BirthdayConstants.PROPERTY_KEYS;

import com.rocket.birthday.api.auth.request.KakaoOAuthTokenRequest;
import com.rocket.birthday.api.auth.response.KakaoOAuthTokenView;
import com.rocket.birthday.api.auth.response.KakaoUserInfoView;
import com.rocket.birthday.api.auth.response.MemberTokenView;
import com.rocket.birthday.api.jwt.JwtTokenProvider;
import com.rocket.birthday.config.auth.KakaoPropertiesConfiguration;
import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.member.vo.ProviderType;
import com.rocket.birthday.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
  private final KakaoAuthClient kakaoAuthClient;
  private final KakaoAPIClient kakaoAPIClient;
  private final KakaoPropertiesConfiguration kakaoPropertiesConfiguration;
  private final MemberService memberService;
  private final MemberProviderService MemberProviderService;
  private final JwtTokenProvider jwtTokenProvider;

  public KakaoOAuthTokenView getKakaoOAuthToken(String code) {

    KakaoOAuthTokenRequest body = KakaoOAuthTokenRequest.builder()
        .grant_type(kakaoPropertiesConfiguration.getGrant_type())
        .client_id(kakaoPropertiesConfiguration.getClient_id())
        .redirect_uri(kakaoPropertiesConfiguration.getRedirect_uri())
        .code(code)
        .build();

    return kakaoAuthClient.getKakaoOAuthToken(body);
  }

  public KakaoUserInfoView getKakaoUserInfo(String accessToken) {
    return kakaoAPIClient.getKakaoUserInfo(
        BEARER + accessToken,
        PROPERTY_KEYS
    );
  }

  public MemberTokenView signUpOrSignInMemberByAuthInfo(String code) {
    KakaoOAuthTokenView oAuthToken = getKakaoOAuthToken(code);
    KakaoUserInfoView kakaoUserInfo = getKakaoUserInfo(oAuthToken.getAccess_token());
    var authInfo = MemberProviderService.findOneBy(String.valueOf(kakaoUserInfo.getId()), ProviderType.KAKAO);

    return authInfo == null ?
        signUpMember(kakaoUserInfo) : signInMember(authInfo.getMember().getId());
  }

  public MemberTokenView signUpMember(KakaoUserInfoView kakaoUserInfo) {
    Member member = memberService.create(kakaoUserInfo);
    MemberProviderService.create(String.valueOf(kakaoUserInfo.getId()), ProviderType.KAKAO, member);

    return getMemberToken(member);
  }

  public MemberTokenView signInMember(Long memberId) {
    Member member = memberService.findOne(memberId);
    return getMemberToken(member);
  }

  public MemberTokenView getMemberToken(Member member) {
    String token = jwtTokenProvider.generateToken(member.getId(), member.getNickname());

    return MemberTokenView.builder()
        .id(member.getId())
        .token(token)
        .build();
  }
}
