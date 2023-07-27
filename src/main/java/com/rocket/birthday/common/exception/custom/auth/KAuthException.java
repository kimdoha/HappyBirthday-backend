package com.rocket.birthday.common.exception.custom.auth;

import com.rocket.birthday.common.exception.KAuthErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KAuthException extends RuntimeException {
  private KAuthErrorCode errorCode;
}
