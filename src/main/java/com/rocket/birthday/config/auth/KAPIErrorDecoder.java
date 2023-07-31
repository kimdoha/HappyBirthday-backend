package com.rocket.birthday.config.auth;

import com.rocket.birthday.common.exception.custom.auth.ForbiddenException;
import com.rocket.birthday.common.exception.custom.auth.InternalServerException;
import com.rocket.birthday.common.exception.custom.auth.InvalidRequestException;
import com.rocket.birthday.common.exception.custom.auth.TooManyRequestException;
import com.rocket.birthday.common.exception.custom.auth.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

// reference. https://developers.kakao.com/docs/latest/ko/reference/rest-api-reference#error-code
public class KAPIErrorDecoder implements ErrorDecoder {
  @Override
  public Exception decode(String methodKey, Response response) {
    switch (response.status()) {
      case 400, 502 -> throw InvalidRequestException.EXCEPTION;
      case 401 -> throw UnauthorizedException.EXCEPTION;
      case 403 -> throw ForbiddenException.EXCEPTION;
      case 429 -> throw TooManyRequestException.EXCEPTION;
      default -> throw InternalServerException.EXCEPTION;
    }
  }
}
