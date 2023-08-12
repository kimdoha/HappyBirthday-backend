package com.rocket.birthday.service.message.mapper;

import com.rocket.birthday.model.message.MessageDeleteEntity;
import com.rocket.birthday.model.message.MessageEntity;
import org.springframework.stereotype.Component;

@Component
public class MessageAssembler {

  public MessageDeleteEntity toMessageDeletedEntity(MessageEntity messageEntity) {
    return MessageDeleteEntity.builder()
        .content( messageEntity.getContent())
        .colorCode( messageEntity.getColorCode())
        .openDate( messageEntity.getOpenDate())
        .to( messageEntity.getTo() == null ? null : messageEntity.getTo().getId())
        .from( messageEntity.getFrom().getId())
        .build();
  }
}
