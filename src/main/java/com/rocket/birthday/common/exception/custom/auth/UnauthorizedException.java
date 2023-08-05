package com.rocket.birthday.common.exception.custom.auth;

import com.rocket.birthday.common.exception.BusinessException;
import com.rocket.birthday.common.exception.enums.BaseErrorCode;

public class UnauthorizedException extends BusinessException {
  public static final UnauthorizedException EXCEPTION = new UnauthorizedException(BaseErrorCode.UNAUTHORIZED_ERROR );
  public UnauthorizedException(BaseErrorCode errorCode) {
    super(errorCode);
  }
}
