package com.rocket.birthday.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.member.QMember;
import com.rocket.birthday.model.member.vo.ProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
  private final JPAQueryFactory queryFactory;
  @Override
  public Member getMemberByProviderId(String proverId, ProviderType type) {
    return queryFactory.select(QMember.member)
        .from(QMember.member)
        .where(QMember.member.memberProvider.providerId.eq(proverId),
            QMember.member.memberProvider.providerType.eq(type))
        .fetchFirst();
  }
}
