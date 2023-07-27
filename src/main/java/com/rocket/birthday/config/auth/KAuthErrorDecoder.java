package com.rocket.birthday.config.auth;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rocket.birthday.common.exception.KAuthErrorCode;
import com.rocket.birthday.common.exception.custom.auth.KAuthException;
import com.rocket.birthday.config.auth.response.KAuthErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;

public class KAuthErrorDecoder implements ErrorDecoder {
  @Override
  public Exception decode(String methodKey, Response response) {
    try (InputStream bodyStream = response.body().asInputStream()) {
      ObjectMapper mapper = new ObjectMapper();
      KAuthErrorResponse body = mapper.readValue(bodyStream, KAuthErrorResponse.class);
      KAuthErrorCode kAuthErrorCode = KAuthErrorCode.valueOf(body.getErrorCode());
      throw new KAuthException(kAuthErrorCode);

    } catch (IOException e) {
      KAuthErrorCode kAuthErrorCode = KAuthErrorCode.INVALID_REQUEST;
      throw new KAuthException(kAuthErrorCode);
    }
  }
}
