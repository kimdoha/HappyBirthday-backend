package com.rocket.birthday.service.auth;

import com.rocket.birthday.model.auth.MemberProviderEntity;
import com.rocket.birthday.model.member.MemberEntity;
import com.rocket.birthday.model.member.vo.ProviderType;
import com.rocket.birthday.repository.auth.MemberProviderRepository;
import com.rocket.birthday.service.auth.mapper.AuthAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberProviderService {
  private final MemberProviderRepository memberProviderRepository;
  private final AuthAssembler authAssembler;

  @Transactional(readOnly = true)
  public MemberProviderEntity findOneBy(String oid, ProviderType provider) {
    return memberProviderRepository.findByOidAndProvider(oid, provider);
  }

  @Transactional
  public MemberProviderEntity create(
      String oid,
      ProviderType provider,
      MemberEntity memberEntity
  ) {
    MemberProviderEntity memberProviderEntity = authAssembler.toMemberProviderEntity(oid, provider,
        memberEntity );
    return memberProviderRepository.save( memberProviderEntity );
  }
}