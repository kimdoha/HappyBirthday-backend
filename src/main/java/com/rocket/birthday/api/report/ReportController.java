package com.rocket.birthday.api.report;

import com.rocket.birthday.api.report.request.CreateMessageReportRequest;
import com.rocket.birthday.api.report.response.MessageReportInfoView;
import com.rocket.birthday.service.member.dto.MemberDetails;
import com.rocket.birthday.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
@RestController
public class ReportController {
  private final ReportService reportService;

  @PostMapping("")
  public MessageReportInfoView createReport(
      @RequestBody CreateMessageReportRequest createMessageReportRequest,
      @AuthenticationPrincipal MemberDetails memberDetails
  ) {
    return reportService.createMessageReport(createMessageReportRequest, memberDetails.getMemberId());
  }
}
