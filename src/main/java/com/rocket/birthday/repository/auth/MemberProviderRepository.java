package com.rocket.birthday.repository.auth;

import com.rocket.birthday.model.auth.MemberProviderEntity;
import com.rocket.birthday.model.member.vo.ProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProviderRepository extends JpaRepository<MemberProviderEntity, Long> {
  MemberProviderEntity findByOidAndProvider(String oid, ProviderType provider);
}