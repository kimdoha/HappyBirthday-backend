package com.rocket.birthday.service.message.dto;

import com.rocket.birthday.model.member.MemberEntity;
import java.time.ZonedDateTime;
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
  private ZonedDateTime openDate;
  private MemberEntity sender;
  private MemberEntity receiver;
}
