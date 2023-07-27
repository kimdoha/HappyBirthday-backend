package com.rocket.birthday.config.auth;

import static com.rocket.birthday.common.exception.BaseErrorCode.KAKAO_EXPIRED_TOKEN;
import static com.rocket.birthday.common.exception.BaseErrorCode.KAKAO_FORBIDDEN_API;
import static com.rocket.birthday.common.exception.BaseErrorCode.KAKAO_INTERNAL_SERVER_ERROR;
import static com.rocket.birthday.common.exception.BaseErrorCode.KAKAO_INVALID_CLIENT;

import com.rocket.birthday.common.exception.custom.auth.ExpiredTokenException;
import com.rocket.birthday.common.exception.custom.auth.ForbiddenException;
import com.rocket.birthday.common.exception.custom.auth.InternalServerException;
import com.rocket.birthday.common.exception.custom.auth.InvalidException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class KakaoAPIErrorDecoder implements ErrorDecoder {
  @Override
  public Exception decode(String methodKey, Response response) {
    switch (response.status()) {
      case 401 -> throw new InvalidException(KAKAO_INVALID_CLIENT);
      case 403 -> throw new ForbiddenException(KAKAO_FORBIDDEN_API);
      case 500 -> throw new ExpiredTokenException(KAKAO_EXPIRED_TOKEN);
      default -> throw new InternalServerException(KAKAO_INTERNAL_SERVER_ERROR);
    }
  }
}
