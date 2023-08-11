package com.rocket.birthday.service.message.factory;

import com.rocket.birthday.model.message.Message;
import com.rocket.birthday.service.message.dto.CreateMessageCommand;
import java.time.ZonedDateTime;
import org.springframework.context.annotation.Bean;

public class DirectMessageFactory {
  @Bean
  public Message create(CreateMessageCommand createMessageCommand) {
    return Message.builder()
        .content(createMessageCommand.getContent())
        .colorCode(createMessageCommand.getColorCode())
        .openDate(createMessageCommand.getOpenDate())
        .to(createMessageCommand.getReceiver())
        .from(createMessageCommand.getSender())
        .build();
  }
}
