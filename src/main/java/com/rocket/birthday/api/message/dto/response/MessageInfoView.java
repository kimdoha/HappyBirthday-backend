package com.rocket.birthday.api.message.dto.response;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MessageInfoView {
  private Long id;
  private String content;
  private String colorCode;
  private String from;
  private String to;
  private ZonedDateTime openDate;
  private ZonedDateTime createdAt;
}
