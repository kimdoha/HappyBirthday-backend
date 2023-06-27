package com.rocket.birthday;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HealthController {
  @PostMapping("/users")
  @ResponseStatus(HttpStatus.OK)
  public SampleResponse returnNameAndAge(@RequestBody SampleRequest request) {
    return SampleResponse.builder()
        .name( request.name )
        .age( request.age )
    .build();

  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SampleRequest {
    private String name;
    private int age;
  }

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SampleResponse {
    private String name;
    private int age;
  }

}