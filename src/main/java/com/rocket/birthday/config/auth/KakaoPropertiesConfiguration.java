package com.rocket.birthday.config.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "kakao")
@JsonNaming(SnakeCaseStrategy.class)
public class KakaoPropertiesConfiguration {
  private String clientId;
  private String redirectUri;
  private String grantType;
}