package com.rocket.birthday.common.exception.custom.auth;

import com.rocket.birthday.common.exception.custom.BusinessException;
import com.rocket.birthday.common.exception.enums.BaseErrorCode;

public class InvalidRequestException extends BusinessException {
  public static final InvalidRequestException EXCEPTION = new InvalidRequestException(BaseErrorCode.INVALID_REQUEST);
  public InvalidRequestException(BaseErrorCode errorCode) {
    super(errorCode);
  }
}
