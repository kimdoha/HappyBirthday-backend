package com.rocket.birthday.api.message.response;

import com.rocket.birthday.model.message.MessageEntity;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageDetailInfoView {
  private Long id;
  private String content;
  private String colorCode;
  private String from;
  private String to;
  private ZonedDateTime openDate;
  private ZonedDateTime createdAt;

  public static MessageDetailInfoView from(MessageEntity messageEntity) {
    return MessageDetailInfoView.builder()
        .id( messageEntity.getId())
        .content( messageEntity.getContent())
        .colorCode( messageEntity.getColorCode())
        .openDate( messageEntity.getOpenDate())
        .to( messageEntity.getTo() == null ? null : messageEntity.getTo().getNickname())
        .from( messageEntity.getFrom().getNickname())
        .createdAt( messageEntity.getCreatedAt())
        .build();
  }
}
