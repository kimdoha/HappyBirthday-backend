package com.rocket.birthday.api.message.response;

import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class MessageInfoView {
  private Long id;
  private String content;
  private String to;
  private ZonedDateTime createdAt;
}
