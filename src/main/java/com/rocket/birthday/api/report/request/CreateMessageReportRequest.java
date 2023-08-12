package com.rocket.birthday.api.report.request;

import com.rocket.birthday.model.report.vo.ReportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageReportRequest {
  private Long messageId;
  private ReportType reportType;
}
