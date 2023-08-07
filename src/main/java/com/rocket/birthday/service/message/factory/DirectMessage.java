package com.rocket.birthday.service.message.factory;

import com.rocket.birthday.model.message.Message;
import com.rocket.birthday.service.message.dto.CreateMessageCommand;
import java.time.ZonedDateTime;

public class DirectMessage {
  public Message from(CreateMessageCommand createMessageCommand) {
    return Message.builder()
        .content(createMessageCommand.getContent())
        .colorCode(createMessageCommand.getColorCode())
        .openDate(ZonedDateTime.parse(createMessageCommand.getOpenDate()))
        .to(createMessageCommand.getReceiver())
        .from(createMessageCommand.getSender())
        .build();
  }
}
