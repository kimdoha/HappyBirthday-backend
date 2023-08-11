package com.rocket.birthday.api.member.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class MemberExistInfoView {
  private Long id;
  private Boolean exist;

  public static MemberExistInfoView from(Long id) {
    return MemberExistInfoView.builder()
        .id(id)
        .exist(true)
        .build();
  }
}
