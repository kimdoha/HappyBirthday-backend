package com.rocket.birthday.service.message.factory;

import com.rocket.birthday.model.message.MessageEntity;
import com.rocket.birthday.service.message.dto.CreateMessageCommand;
import org.springframework.context.annotation.Bean;

public class SharedMessageFactory {

  @Bean
  public MessageEntity create(CreateMessageCommand createMessageCommand) {
    return MessageEntity.builder()
        .content(createMessageCommand.getContent())
        .colorCode(createMessageCommand.getColorCode())
        .openDate(createMessageCommand.getOpenDate())
        .to(null)
        .from(createMessageCommand.getSender())
        .build();
  }
}
