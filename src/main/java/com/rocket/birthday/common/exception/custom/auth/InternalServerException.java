package com.rocket.birthday.common.exception.custom.auth;

import static com.rocket.birthday.common.exception.BaseErrorCode.KAKAO_INTERNAL_SERVER_ERROR;

import com.rocket.birthday.common.exception.BusinessException;
import com.rocket.birthday.common.exception.BaseErrorCode;

public class InternalServerException extends BusinessException {
  public InternalServerException(BaseErrorCode errorCode) {
    super(KAKAO_INTERNAL_SERVER_ERROR);
  }
}
