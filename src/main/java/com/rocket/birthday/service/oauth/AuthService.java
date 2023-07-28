package com.rocket.birthday.service.oauth;

import com.rocket.birthday.api.auth.request.KakaoOAuthTokenRequest;
import com.rocket.birthday.api.auth.response.KakaoOAuthTokenView;
import com.rocket.birthday.api.auth.response.KakaoUserInfoView;
import com.rocket.birthday.api.auth.response.MemberTokenView;
import com.rocket.birthday.api.jwt.JwtTokenProvider;
import com.rocket.birthday.config.auth.KakaoPropertiesConfiguration;
import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.member.vo.ProviderType;
import com.rocket.birthday.model.oauth.OAuthProvider;
import com.rocket.birthday.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

  private static final String BEARER = "Bearer ";
  private static final String PROPERTY_KEYS = "[\"kakao_account.profile\", \"kakao_account.name\", \"kakao_account.email\"]";

  private final KakaoAuthClient kakaoAuthClient;
  private final KakaoAPIClient kakaoAPIClient;
  private final KakaoPropertiesConfiguration kakaoPropertiesConfiguration;
  private final MemberService memberService;
  private final OAuthProviderService OAuthProviderService;
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

  public MemberTokenView signInMember(String code) {
    KakaoOAuthTokenView oAuthToken = getKakaoOAuthToken(code);
    log.info(oAuthToken.getAccess_token());
    KakaoUserInfoView kakaoUserInfo = getKakaoUserInfo(oAuthToken.getAccess_token());
    var authInfo = OAuthProviderService.findOneBy(String.valueOf(kakaoUserInfo.getId()), ProviderType.KAKAO);

    if(authInfo == null) {
      return signUpMember(kakaoUserInfo);
    }

    Member member = memberService.findOne(authInfo.getId());
    return getMemberToken(member);
  }

  public MemberTokenView signUpMember(KakaoUserInfoView kakaoUserInfo) {
    Member member = memberService.create(kakaoUserInfo);
    OAuthProviderService.create(String.valueOf(kakaoUserInfo.getId()), ProviderType.KAKAO, member);

    return getMemberToken(member);
  }

  public MemberTokenView getMemberToken(Member member) {
    log.info("memberInfo : " + member.getNickname() + " " + member.getId());
    String token = jwtTokenProvider.generateToken(member.getId(), member.getNickname());
    log.info("token : " + token);
    return MemberTokenView.builder()
        .id(member.getId())
        .token(token)
        .build();
  }
}
