package com.rocket.birthday.common.exception.custom.auth;

import com.rocket.birthday.common.exception.BusinessException;
import com.rocket.birthday.common.exception.BaseErrorCode;

public class UnauthorizedException extends BusinessException {

  public UnauthorizedException(BaseErrorCode errorCode) {
    super( errorCode );
  }
}
