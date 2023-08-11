package com.rocket.birthday.common.exception.custom.message;

import com.rocket.birthday.common.exception.BusinessException;
import com.rocket.birthday.common.exception.enums.BaseErrorCode;

public class InvalidMessageRequestException extends BusinessException {

  public InvalidMessageRequestException(BaseErrorCode errorCode) {
    super(errorCode);
  }
}
