package com.rocket.birthday.api.message.response;

import com.rocket.birthday.model.message.MessageEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class TodayMessageInfoView {
  private Long id;
  private String content;
  private String colorCode;
  private String from;

  public static TodayMessageInfoView from(MessageEntity messageEntity) {
    return TodayMessageInfoView.builder()
        .id( messageEntity.getId())
        .content( messageEntity.getContent())
        .colorCode( messageEntity.getColorCode())
        .from( messageEntity.getFrom().getNickname())
        .build();
  }
}
