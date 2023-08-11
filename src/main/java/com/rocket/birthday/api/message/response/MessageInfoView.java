package com.rocket.birthday.api.message.response;

import com.rocket.birthday.model.message.Message;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageInfoView {
  private Long id;
  private String content;
  private String colorCode;
  private String from;
  private String to;
  private ZonedDateTime openDate;
  private ZonedDateTime createdAt;

  public static MessageInfoView from(Message message) {
    return MessageInfoView.builder()
        .id(message.getId())
        .content(message.getContent())
        .colorCode(message.getColorCode())
        .openDate(message.getOpenDate())
        .to(message.getTo() == null ? null : message.getTo().getNickname())
        .from(message.getFrom().getNickname())
        .createdAt(message.getCreatedAt())
        .build();
  }
}
