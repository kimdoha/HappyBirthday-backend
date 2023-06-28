package com.rocket.birthday;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/samples")
@RestController
public class SampleController {

  @GetMapping("/{sampleId}")
  public SampleResponse getSampleById(
      @PathVariable String sampleId
  ) {
    return new SampleResponse(sampleId, "sample");
  }

  @Getter
  @AllArgsConstructor
  public class SampleResponse {
    private final String sampleId;
    private final String name;
  }
}