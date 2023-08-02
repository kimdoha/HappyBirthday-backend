package com.rocket.birthday.service.message;

import com.rocket.birthday.api.message.request.PostMessageRequest;
import com.rocket.birthday.model.message.Message;
import com.rocket.birthday.repository.message.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
  private final MessageRepository messageRepository;

//  public Message createMessage(Long memberId, PostMessageRequest postMessageRequest) {
////    Message message = Message.builder.build();
////    return messageRepository.save(  );
//
//  }
}
