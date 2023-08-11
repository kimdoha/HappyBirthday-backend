package com.rocket.birthday.api.auth.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class MemberTokenView {
  private Long id;
  private String token;

  public static MemberTokenView of(
      Long id,
      String token
  ) {
    return MemberTokenView.builder()
        .id(id)
        .token(token)
        .build();
  }
}
