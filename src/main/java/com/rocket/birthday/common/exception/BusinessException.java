package com.rocket.birthday.common.exception;

import com.rocket.birthday.common.exception.enums.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
  private BaseErrorCode errorCode;
}
