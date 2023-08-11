package com.rocket.birthday.service.message.mapper;

import com.rocket.birthday.model.message.Message;
import com.rocket.birthday.model.message.MessageDeleted;
import org.springframework.stereotype.Component;

@Component
public class MessageAssembler {

  public MessageDeleted toMessageDeletedEntity(Message message) {
    return MessageDeleted.builder()
        .content(message.getContent())
        .colorCode(message.getColorCode())
        .openDate(message.getOpenDate())
        .to(message.getTo().getId())
        .from(message.getFrom().getId())
        .build();
  }
}
