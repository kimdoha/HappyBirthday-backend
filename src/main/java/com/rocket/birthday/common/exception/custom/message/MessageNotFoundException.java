package com.rocket.birthday.common.exception.custom.message;

import static com.rocket.birthday.common.exception.enums.BaseErrorCode.MESSAGE_NOT_FOUND;

import com.rocket.birthday.common.exception.BusinessException;
import com.rocket.birthday.common.exception.enums.BaseErrorCode;

public class MessageNotFoundException extends BusinessException {
  public MessageNotFoundException(BaseErrorCode errorCode) {
    super(errorCode);
  }
}
