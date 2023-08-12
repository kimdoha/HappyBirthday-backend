package com.rocket.birthday.repository.member;

import com.rocket.birthday.model.member.MemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>, MemberRepositoryCustom {
  Optional<MemberEntity> findByNickname(String nickname);
}
