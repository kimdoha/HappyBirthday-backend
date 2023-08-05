package com.rocket.birthday.api.message.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageExistInfoView {
  private Long id;
  private Boolean exist;
}
