package com.rocket.birthday.config.auth;

import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(KAPIErrorDecoder.class)
public class KakaoAPIConfiguration {

  @Bean
  @ConditionalOnMissingBean(value = ErrorDecoder.class)
  public KAPIErrorDecoder commonFeignErrorDecoder() {
    return new KAPIErrorDecoder();
  }
}