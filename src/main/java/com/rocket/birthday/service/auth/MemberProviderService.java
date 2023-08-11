package com.rocket.birthday.service.auth;

import com.rocket.birthday.model.auth.MemberProvider;
import com.rocket.birthday.model.member.Member;
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
  public MemberProvider findOneBy(String oid, ProviderType provider) {
    return memberProviderRepository.findByOidAndProvider(oid, provider);
  }

  @Transactional
  public MemberProvider create(
      String oid,
      ProviderType provider,
      Member member
  ) {
    MemberProvider memberProvider = authAssembler.toMemberProviderEntity(oid, provider, member);
    return memberProviderRepository.save(memberProvider);
  }
}