package com.rocket.birthday.service.auth;

import com.rocket.birthday.api.auth.request.KakaoOAuthTokenRequest;
import com.rocket.birthday.api.auth.response.KakaoOAuthTokenView;
import com.rocket.birthday.api.auth.response.KakaoUserInfoView;
import com.rocket.birthday.api.auth.response.MemberTokenView;
import com.rocket.birthday.api.jwt.JwtTokenProvider;
import com.rocket.birthday.config.KakaoPropertiesConfiguration;
import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.member.MemberProvider;
import com.rocket.birthday.model.member.vo.ProviderType;
import com.rocket.birthday.service.member.MemberProviderService;
import com.rocket.birthday.service.member.MemberService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

  private static final String BEARER = "Bearer ";
  private static final String PROPERTY_KEYS = "[\"kakao_account.profile\", \"kakao_account.name\", \"kakao_account.email\"]";

  private final KakaoAuthClient kakaoAuthClient;
  private final KakaoAPIClient kakaoAPIClient;
  private final KakaoPropertiesConfiguration kakaoPropertiesConfiguration;
  private final MemberService memberService;
  private final MemberProviderService memberProviderService;
  private final JwtTokenProvider jwtTokenProvider;

  public KakaoOAuthTokenView getKakaoOAuthToken(String code) {

    KakaoOAuthTokenRequest body = KakaoOAuthTokenRequest.builder()
        .grant_type( kakaoPropertiesConfiguration.getGrant_type())
        .client_id( kakaoPropertiesConfiguration.getClient_id())
        .redirect_uri( kakaoPropertiesConfiguration.getRedirect_uri())
        .code(code)
        .build();

    return kakaoAuthClient.getKakaoOAuthToken(body);
  }

  public KakaoUserInfoView getKakaoUserInfo(String accessToken) {
    return kakaoAPIClient.getKakaoUserInfo( BEARER + accessToken, PROPERTY_KEYS);
  }

  public MemberTokenView signUpMember(String code) {
    KakaoOAuthTokenView kakaoOAuthToken = getKakaoOAuthToken(code);
    KakaoUserInfoView kakaoUser = getKakaoUserInfo(kakaoOAuthToken.getAccess_token());

    if(kakaoUser.getId() == null ||
        !kakaoUser.getKakao_account().getHas_email()) {
        // 오류
    }

    MemberProvider memberProvider = memberProviderService.createMemberProvider(kakaoUser.getId(), ProviderType.KAKAO);

    Member member = memberService.createMember(kakaoUser.getKakao_account().getEmail(),
        kakaoUser.getKakao_account().getProfile().getNickname(),
        kakaoUser.getKakao_account().getProfile().getProfile_image_url(),
        LocalDate.now(),
        memberProvider);

    return getMemberToken(member);
  }

  private MemberTokenView getMemberToken(Member member) {
    String token = jwtTokenProvider.generateToken(member.getId(), member.getName());
    return MemberTokenView.builder()
        .id(member.getId())
        .token(token)
        .build();
  }
}
