package com.rocket.birthday.repository.oauth;

import com.rocket.birthday.model.member.vo.ProviderType;
import com.rocket.birthday.model.oauth.OAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthProviderRepository extends JpaRepository<OAuthProvider, Long> {
  OAuthProvider findOAuthProviderByOidAndProvider(String oid, ProviderType provider);
}