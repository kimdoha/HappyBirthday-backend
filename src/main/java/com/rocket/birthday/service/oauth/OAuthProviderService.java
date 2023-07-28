package com.rocket.birthday.service.oauth;

import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.member.vo.ProviderType;
import com.rocket.birthday.model.oauth.OAuthProvider;
import com.rocket.birthday.repository.oauth.OAuthProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OAuthProviderService {
  private final OAuthProviderRepository oAuthProviderRepository;

  @Transactional(readOnly = true)
  public OAuthProvider findOneBy(String oid, ProviderType provider) {
    return oAuthProviderRepository.findOAuthProviderByOidAndProvider(oid, provider);
  }

  @Transactional
  public OAuthProvider create(String oid, ProviderType provider, Member member) {
    OAuthProvider oAuthProvider = OAuthProvider.builder()
        .member(member)
        .oid(oid)
        .provider(provider)
        .build();

    return oAuthProviderRepository.save(oAuthProvider);
  }
}