package com.rocket.birthday.api.report.response;

import com.rocket.birthday.model.report.ReportEntity;
import com.rocket.birthday.model.report.vo.ReportType;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageReportInfoView {
  private Long reportId;
  private ReportType reportType;
  private String reporter;
  private Long messageId;
  private String messageContent;
  private ZonedDateTime createdAt;

  public static MessageReportInfoView from(ReportEntity reportEntity) {
    return MessageReportInfoView.builder()
        .reportId(reportEntity.getId())
        .reportType(reportEntity.getReportType())
        .reporter(reportEntity.getMemberEntity().getNickname())
        .messageId(reportEntity.getMessageEntity().getId())
        .messageContent(reportEntity.getMessageEntity().getContent())
        .createdAt(reportEntity.getCreatedAt())
        .build();
  }
}
