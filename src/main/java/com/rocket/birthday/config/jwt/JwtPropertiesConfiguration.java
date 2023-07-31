package com.rocket.birthday.config.jwt;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
@JsonNaming(SnakeCaseStrategy.class)
public class JwtPropertiesConfiguration {
  private String secretKey;
  private Long expireTime;
}
