package com.rocket.birthday.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class OffsetPagingView {
  protected OffsetPagingInfoView page;

}
