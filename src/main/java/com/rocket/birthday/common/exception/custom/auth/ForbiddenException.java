package com.rocket.birthday.common.exception.custom.auth;

import static com.rocket.birthday.common.exception.BaseErrorCode.KAKAO_FORBIDDEN_API;

import com.rocket.birthday.common.exception.BusinessException;
import com.rocket.birthday.common.exception.BaseErrorCode;

public class ForbiddenException extends BusinessException {
  public ForbiddenException(BaseErrorCode errorCode) {
    super(KAKAO_FORBIDDEN_API);
  }
}
