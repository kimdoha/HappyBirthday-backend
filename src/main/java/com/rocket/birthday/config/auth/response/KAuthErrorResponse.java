package com.rocket.birthday.config.auth.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import feign.Response;
import java.io.InputStream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// reference. https://developers.kakao.com/docs/latest/ko/reference/rest-api-reference
@Getter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class KAuthErrorResponse {
  private String errorCode;
  private String errorDescription;
}
