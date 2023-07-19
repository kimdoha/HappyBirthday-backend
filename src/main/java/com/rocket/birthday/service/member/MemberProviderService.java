package com.rocket.birthday.service.member;

import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.member.MemberProvider;
import com.rocket.birthday.model.member.vo.ProviderType;
import com.rocket.birthday.repository.MemberProviderRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberProviderService {
  private final MemberProviderRepository memberProviderRepository;

  public Optional<MemberProvider> findOneByProviderTypeAndProviderId(ProviderType providerType, String providerId) {
    return memberProviderRepository.findOneByProviderTypeAndProviderId(providerType, providerId);
  }
}