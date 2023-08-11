package com.rocket.birthday.service.auth.mapper;

import com.rocket.birthday.api.auth.request.KakaoOAuthTokenRequest;
import com.rocket.birthday.api.auth.response.MemberTokenView;
import com.rocket.birthday.model.auth.MemberProvider;
import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.member.vo.ProviderType;
import org.springframework.stereotype.Component;

@Component
public class AuthAssembler {

  public MemberProvider toMemberProviderEntity (
      String oid,
      ProviderType provider,
      Member member
  ) {
    return MemberProvider.builder()
        .member(member)
        .oid(oid)
        .provider(provider)
        .build();
  }
}
