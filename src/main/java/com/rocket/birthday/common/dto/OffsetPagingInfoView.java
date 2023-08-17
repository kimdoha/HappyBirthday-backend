package com.rocket.birthday.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OffsetPagingInfoView {
  private Integer offset;
  private Integer limit;

  public static OffsetPagingInfoView of(
      Integer offset,
      Integer limit
  ) {
    return OffsetPagingInfoView.builder()
        .offset(offset)
        .limit(limit)
        .build();
  }
}
