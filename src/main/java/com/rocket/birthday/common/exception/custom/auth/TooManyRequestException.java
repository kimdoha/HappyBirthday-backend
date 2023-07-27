package com.rocket.birthday.common.exception.custom.auth;

import com.rocket.birthday.common.exception.BusinessException;
import com.rocket.birthday.common.exception.enums.BaseErrorCode;

public class TooManyRequestException extends BusinessException {
  public static TooManyRequestException EXCEPTION = new TooManyRequestException(BaseErrorCode.TOO_MANY_REQUEST_ERROR);
  public TooManyRequestException(BaseErrorCode errorCode) {
    super(errorCode);
  }
}
