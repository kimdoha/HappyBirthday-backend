package com.rocket.birthday.repository;

import com.rocket.birthday.model.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>,  MemberRepositoryCustom {
}
