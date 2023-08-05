package com.rocket.birthday.api.message.mapper;

import com.rocket.birthday.api.message.dto.request.PostMessageRequest;
import com.rocket.birthday.api.message.dto.response.MessageInfoView;
import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.message.Message;
import java.time.ZonedDateTime;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

  public Message toEntity(
      PostMessageRequest postMessageRequest,
      Member toMember,
      Member fromMember,
      Boolean receiverExist) {

    return Message.builder()
        .content(postMessageRequest.getContent())
        .colorCode(postMessageRequest.getColorCode())
        .to(receiverExist ? toMember : null)
        .from(fromMember)
        .openDate(ZonedDateTime.parse(postMessageRequest.getOpenDate()))
        .build();
  }

  public MessageInfoView toMessageInfoView(Message message) {
    System.out.println(message);
    return MessageInfoView.builder()
        .id(message.getId())
        .content(message.getContent())
        .colorCode(message.getColorCode())
        .to(message.getTo() == null ? null : message.getTo().getNickname())
        .from(message.getFrom().getNickname())
        .openDate(message.getOpenDate())
        .createdAt(message.getCreatedAt())
        .build();
  }
}
