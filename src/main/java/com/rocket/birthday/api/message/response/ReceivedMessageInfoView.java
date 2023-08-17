package com.rocket.birthday.api.message.response;

import com.rocket.birthday.model.message.MessageEntity;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReceivedMessageInfoView {
  private Long id;
  private String content;
  private ZonedDateTime openDate;
  private Boolean lock;
  private ZonedDateTime createdAt;

  public static ReceivedMessageInfoView from(
      MessageEntity messageEntity
  ) {
    return ReceivedMessageInfoView.builder()
        .id(messageEntity.getId())
        .content(messageEntity.getContent())
        .openDate(messageEntity.getOpenDate())
        .lock(ZonedDateTime.now().isBefore(messageEntity.getOpenDate()))
        .createdAt(messageEntity.getCreatedAt())
        .build();
  }
}
