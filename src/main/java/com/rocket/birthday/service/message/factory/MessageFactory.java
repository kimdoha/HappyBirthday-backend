package com.rocket.birthday.service.message.factory;

import com.rocket.birthday.model.message.Message;
import com.rocket.birthday.service.message.dto.CreateMessageCommand;
import com.rocket.birthday.service.message.vo.MessageType;
import org.springframework.stereotype.Component;

@Component
public class MessageFactory {
  public Message create(
      MessageType messageType,
      CreateMessageCommand createMessageCommand
  ) {
    Message message = null;
    if(messageType.equals(MessageType.DIRECT)) message = DirectMessageFactory.from(createMessageCommand);
    if(messageType.equals(MessageType.SHARED)) message = SharedMessageFactory.from(createMessageCommand);
    return message;
  }
}
