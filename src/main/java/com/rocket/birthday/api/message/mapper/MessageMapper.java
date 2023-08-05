package com.rocket.birthday.api.message.mapper;

import static com.rocket.birthday.common.constant.BirthdayConstants.formatter;

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
      Member fromMember) {
    return Message.builder()
        .content( postMessageRequest.getContent())
        .colorCode( postMessageRequest.getColorCode())
        .to(toMember)
        .from(fromMember)
        .openDate(ZonedDateTime.parse(postMessageRequest.getOpenDate(), formatter))
        .build();
  }

  public MessageInfoView toMessageInfoView(Message message) {
    return MessageInfoView.builder()
        .content(message.getContent())
        .colorCode(message.getColorCode())
        .to(message.getTo().getNickname())
        .from(message.getFrom().getNickname())
        .openDate(message.getOpenDate())
        .createdAt(message.getCreatedAt())
        .build();
  }
}
