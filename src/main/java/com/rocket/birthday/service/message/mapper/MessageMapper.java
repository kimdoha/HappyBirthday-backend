package com.rocket.birthday.service.message.mapper;

import com.rocket.birthday.api.message.response.MessageExistInfoView;
import com.rocket.birthday.api.message.response.MessageInfoView;
import com.rocket.birthday.model.message.Message;
import com.rocket.birthday.model.message.MessageDeleted;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

  public static MessageInfoView toMessageInfoView(Message message) {

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

  public MessageDeleted toMessageDeletedEntity(Message message) {
    return MessageDeleted.builder()
        .content(message.getContent())
        .colorCode(message.getColorCode())
        .openDate(message.getOpenDate())
        .to(message.getTo().getId())
        .from(message.getFrom().getId())
        .build();
  }

  public static MessageExistInfoView toMessageExistInfoView(Long id) {
    return MessageExistInfoView.builder()
        .id(id)
        .exist(false)
        .build();
  }
}
