package com.rocket.birthday.common.exception.custom.auth;

import com.rocket.birthday.common.exception.BusinessException;
import com.rocket.birthday.common.exception.BaseErrorCode;

public class InvalidException extends BusinessException {
  public InvalidException(BaseErrorCode errorCode) {
    super(errorCode);
  }
}
