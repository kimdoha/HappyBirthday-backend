package com.rocket.birthday.service.member;

import com.rocket.birthday.model.member.MemberProvider;
import com.rocket.birthday.model.member.vo.ProviderType;
import com.rocket.birthday.repository.MemberProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberProviderService {
  private final MemberProviderRepository memberProviderRepository;

  @Transactional
  public MemberProvider createMemberProvider(Long providerId, ProviderType loginType) {
    MemberProvider memberProvider = MemberProvider.builder()
        .providerId(providerId)
        .providerType(loginType)
        .build();

    return memberProviderRepository.save(memberProvider);
  }
}