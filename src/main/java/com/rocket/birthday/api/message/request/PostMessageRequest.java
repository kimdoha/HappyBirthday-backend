package com.rocket.birthday.api.message.request;


import com.rocket.birthday.model.member.MemberEntity;
import com.rocket.birthday.service.message.dto.CreateMessageCommand;
import com.rocket.birthday.service.message.vo.MessageType;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostMessageRequest {
  private String content;
  private String colorCode;
  private ZonedDateTime openDate;
  private MessageType messageType;
  private Long receiverId;

  public CreateMessageCommand toCommand(
      MemberEntity sender,
      MemberEntity receiver
  ) {
    return CreateMessageCommand.builder()
        .content(this.content)
        .colorCode(this.colorCode)
        .openDate(this.openDate)
        .sender(sender)
        .receiver(receiver)
        .build();
  }
}
