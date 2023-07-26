package com.rocket.birthday.repository;

import com.rocket.birthday.model.member.MemberProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProviderRepository extends JpaRepository<MemberProvider, Long> {

}
