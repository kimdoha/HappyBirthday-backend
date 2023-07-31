package com.rocket.birthday.common.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KAuthErrorResponse {
  private Integer code;
  private String errorCode;
  private String error;
  private String reason;
}
