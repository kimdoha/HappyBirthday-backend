package com.rocket.birthday.repository.auth;

import com.rocket.birthday.model.auth.MemberProvider;
import com.rocket.birthday.model.member.vo.ProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProviderRepository extends JpaRepository<MemberProvider, Long> {
  MemberProvider findByOidAndProvider(String oid, ProviderType provider);
}