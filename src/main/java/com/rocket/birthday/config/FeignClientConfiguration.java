package com.rocket.birthday.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(
    basePackages = {"com.rocket.birthday"}
)
public class FeignClientConfiguration {
}
