package com.rocket.birthday.api.member.mapper;

import com.rocket.birthday.api.member.dto.response.MemberExistInfoView;
import com.rocket.birthday.model.member.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
  public MemberExistInfoView toMemberExistInfoView(Long id) {
    return MemberExistInfoView.builder()
        .id(id)
        .exist(true)
        .build();
  }
}
