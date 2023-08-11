package com.rocket.birthday.api.message.request;


import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.service.message.dto.CreateMessageCommand;
import com.rocket.birthday.service.message.vo.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostMessageRequest {
  private String content;
  private String colorCode;
  private String openDate;
  private MessageType messageType;
  private Long receiverId;

  public CreateMessageCommand toCommand(
      Member sender,
      Member receiver
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
