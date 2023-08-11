package com.rocket.birthday.api.message.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class MessageExistInfoView {
  private Long id;
  private Boolean exist;

  public static MessageExistInfoView from(Long id) {
    return MessageExistInfoView.builder()
        .id(id)
        .exist(true)
        .build();
  }
}
