package com.rocket.birthday.config.auth;

import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(KAuthErrorDecoder.class)
public class KakaoAuthConfiguration {
  @Bean
  @ConditionalOnMissingBean(value = ErrorDecoder.class)
  public KAuthErrorDecoder commonFeignErrorDecoder() {
    return new KAuthErrorDecoder();
  }
}