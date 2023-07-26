package com.rocket.birthday.repository;

import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.member.vo.ProviderType;

public interface MemberRepositoryCustom {
  Member getMemberByProviderId(String proverId, ProviderType type);
}
