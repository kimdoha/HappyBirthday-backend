package com.rocket.birthday.service.message.dto;

import com.rocket.birthday.model.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageCommand {
  private String content;
  private String colorCode;
  private String openDate;
  private Member sender;
  private Member receiver;
}
