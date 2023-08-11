package com.rocket.birthday.api.message.response;

import com.rocket.birthday.model.message.Message;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class MessageInfoView {
  private Long id;
  private String content;
  private String colorCode;
  private String from;

  public static MessageInfoView from(Message message) {
    return MessageInfoView.builder()
        .id(message.getId())
        .content(message.getContent())
        .colorCode(message.getColorCode())
        .from(message.getFrom().getNickname())
        .build();
  }
}
