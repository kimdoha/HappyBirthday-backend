package com.rocket.birthday.common.exception.custom.auth;

import com.rocket.birthday.common.exception.custom.BusinessException;
import com.rocket.birthday.common.exception.enums.BaseErrorCode;

public class InternalServerException extends BusinessException {
  public static final InternalServerException EXCEPTION = new InternalServerException(BaseErrorCode.INNER_SERVER_ERROR);
  public InternalServerException(BaseErrorCode errorCode) {
    super(errorCode);
  }
}
