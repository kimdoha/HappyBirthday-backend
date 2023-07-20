package com.rocket.birthday.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "kakao")
public class KakaoPropertiesConfiguration {
  private String client_id;
  private String redirect_uri;
  private String grant_type;
}