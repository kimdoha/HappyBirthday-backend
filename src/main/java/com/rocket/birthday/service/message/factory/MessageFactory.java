package com.rocket.birthday.service.message.factory;

import com.rocket.birthday.model.message.MessageEntity;
import com.rocket.birthday.service.message.dto.CreateMessageCommand;
import com.rocket.birthday.service.message.vo.MessageType;
import org.springframework.stereotype.Component;

@Component
public class MessageFactory {
  public MessageEntity create(
      MessageType messageType,
      CreateMessageCommand createMessageCommand
  ) {
    MessageEntity messageEntity = null;
    if(messageType.equals(MessageType.DIRECT)) messageEntity = new DirectMessageFactory().create(createMessageCommand);
    if(messageType.equals(MessageType.SHARED)) messageEntity = new SharedMessageFactory().create(createMessageCommand);
    return messageEntity;
  }
}
