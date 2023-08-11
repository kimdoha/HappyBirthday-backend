package com.rocket.birthday.api.message.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMessageRequest {
  private String content;
  private String openDate;
  private String colorCode;
}
