package com.rocket.birthday.service.auth.mapper;

import com.rocket.birthday.api.auth.request.KakaoOAuthTokenRequest;
import com.rocket.birthday.api.auth.response.MemberTokenView;
import com.rocket.birthday.model.auth.MemberProvider;
import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.member.vo.ProviderType;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
  public KakaoOAuthTokenRequest toKakaoOAuthTokenRequest(String grantType, String clientId, String redirectUri, String code) {
    return KakaoOAuthTokenRequest.builder()
        .grant_type(grantType)
        .client_id(clientId)
        .redirect_uri(redirectUri)
        .code(code)
        .build();
  }

  public MemberTokenView toMemberTokenView(Long memberId, String token) {
    return MemberTokenView.builder()
        .id(memberId)
        .token(token)
        .build();
  }

  public MemberProvider toMemberProvider(String oid, ProviderType provider, Member member) {
    return MemberProvider.builder()
        .member(member)
        .oid(oid)
        .provider(provider)
        .build();
  }
}
