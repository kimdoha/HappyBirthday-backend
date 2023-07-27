package com.rocket.birthday.common.exception.custom.auth;

import static com.rocket.birthday.common.exception.BaseErrorCode.KAKAO_EXPIRED_TOKEN;

import com.rocket.birthday.common.exception.BusinessException;
import com.rocket.birthday.common.exception.BaseErrorCode;

public class ExpiredTokenException extends BusinessException {
  public ExpiredTokenException(BaseErrorCode errorCode) {
    super(KAKAO_EXPIRED_TOKEN);
  }
}
