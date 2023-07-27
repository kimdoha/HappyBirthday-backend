package com.rocket.birthday.common.exception.custom.auth;

import com.rocket.birthday.common.exception.BusinessException;
import com.rocket.birthday.common.exception.enums.BaseErrorCode;

public class ForbiddenException extends BusinessException {
  public static ForbiddenException EXCEPTION = new ForbiddenException(BaseErrorCode.FORBIDDEN_ERROR);
  public ForbiddenException(BaseErrorCode errorCode) {
    super(errorCode);
  }
}
