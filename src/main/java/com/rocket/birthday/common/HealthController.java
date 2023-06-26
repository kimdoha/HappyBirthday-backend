package com.rocket.birthday.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("health")
@RestController
public class HealthController {
  @GetMapping("")
  public String health() {
    return "Health OK!";
  }
}
