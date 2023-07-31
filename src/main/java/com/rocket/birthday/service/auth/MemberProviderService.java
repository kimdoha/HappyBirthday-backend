package com.rocket.birthday.service.auth;

import com.rocket.birthday.model.auth.MemberProvider;
import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.member.vo.ProviderType;
import com.rocket.birthday.repository.auth.MemberProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberProviderService {
  private final MemberProviderRepository memberProviderRepository;

  @Transactional(readOnly = true)
  public MemberProvider findOneBy(String oid, ProviderType provider) {
    return memberProviderRepository.findByOidAndProvider(oid, provider);
  }

  @Transactional
  public MemberProvider create(String oid, ProviderType provider, Member member) {
    MemberProvider memberProvider = MemberProvider.builder()
        .member(member)
        .oid(oid)
        .provider(provider)
        .build();

    return memberProviderRepository.save(memberProvider);
  }
}