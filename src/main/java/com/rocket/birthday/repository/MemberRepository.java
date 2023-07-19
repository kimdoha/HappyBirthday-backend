package com.rocket.birthday.repository;

import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.member.vo.ProviderType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findMemberByMemberProviderId(Long memberProviderId);
  Optional<Member> findMemberByEmail(String email);
}
