package com.rocket.birthday.service.auth;

import static com.rocket.birthday.common.constant.BirthdayConstants.BEARER;
import static com.rocket.birthday.common.constant.BirthdayConstants.PROPERTY_KEYS;

import com.rocket.birthday.api.auth.request.KakaoOAuthTokenRequest;
import com.rocket.birthday.api.auth.response.KakaoOAuthTokenView;
import com.rocket.birthday.api.auth.response.KakaoUserInfoView;
import com.rocket.birthday.api.auth.response.MemberTokenView;
import com.rocket.birthday.service.auth.mapper.AuthMapper;
import com.rocket.birthday.service.jwt.JwtTokenProvider;
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
  private final MemberProviderService memberProviderService;
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthMapper authMapper;

  public KakaoOAuthTokenView getKakaoOAuthToken(String code) {

    KakaoOAuthTokenRequest body = authMapper.toKakaoOAuthTokenRequest(
        kakaoPropertiesConfiguration.getGrantType(),
        kakaoPropertiesConfiguration.getClientId(),
        kakaoPropertiesConfiguration.getRedirectUri(),
        code
    );

    return kakaoAuthClient.getKakaoOAuthToken(body);
  }

  public KakaoUserInfoView getKakaoUserInfo(String accessToken) {
    return kakaoAPIClient.getKakaoUserInfo( BEARER + accessToken, PROPERTY_KEYS );
  }

  public MemberTokenView signUpOrSignInMemberByAuthInfo(String code) {
    KakaoOAuthTokenView oAuthToken = getKakaoOAuthToken(code);
    KakaoUserInfoView kakaoUserInfo = getKakaoUserInfo(oAuthToken.getAccess_token());
    var authInfo = memberProviderService.findOneBy(String.valueOf(kakaoUserInfo.getId()), ProviderType.KAKAO);

    return authInfo == null ?
        signUpMember(kakaoUserInfo) : signInMember(authInfo.getMember().getId());
  }

  public MemberTokenView signUpMember(KakaoUserInfoView kakaoUserInfo) {
    Member member = memberService.create(kakaoUserInfo);
    memberProviderService.create(String.valueOf(kakaoUserInfo.getId()), ProviderType.KAKAO, member);

    return getMemberToken(member);
  }

  public MemberTokenView signInMember(Long memberId) {
    Member member = memberService.findOne(memberId);
    return getMemberToken(member);
  }

  public MemberTokenView getMemberToken(Member member) {
    String token = jwtTokenProvider.generateToken(member.getId(), member.getNickname());

    return authMapper.toMemberTokenView(member.getId(), token);
  }
}
