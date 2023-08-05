package com.rocket.birthday.api.message.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostMessageRequest {
  private String content;
  private Long receiver;
  private String colorCode;
  private String openDate;
}
