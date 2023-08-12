package com.rocket.birthday.service.auth.mapper;

import com.rocket.birthday.model.auth.MemberProviderEntity;
import com.rocket.birthday.model.member.MemberEntity;
import com.rocket.birthday.model.member.vo.ProviderType;
import org.springframework.stereotype.Component;

@Component
public class AuthAssembler {

  public MemberProviderEntity toMemberProviderEntity (
      String oid,
      ProviderType provider,
      MemberEntity memberEntity
  ) {
    return MemberProviderEntity.builder()
        .memberEntity( memberEntity )
        .oid(oid)
        .provider(provider)
        .build();
  }
}
