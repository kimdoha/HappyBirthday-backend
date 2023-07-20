package com.rocket.birthday.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt.token")
public class JwtPropertiesConfiguration {
  private String secret_key;
  private Long expire_time;
}
