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
    if(messageType.equals(MessageType.DIRECT)) message = new DirectMessageFactory().create(createMessageCommand);
    if(messageType.equals(MessageType.SHARED)) message = new SharedMessageFactory().create(createMessageCommand);
    return message;
  }
}
