package com.rocket.birthday.service.report.mapper;

import com.rocket.birthday.model.member.MemberEntity;
import com.rocket.birthday.model.message.MessageEntity;
import com.rocket.birthday.model.report.ReportEntity;
import com.rocket.birthday.model.report.vo.ReportType;
import org.springframework.stereotype.Component;

@Component
public class ReportAssembler {
  public ReportEntity assemble(
      ReportType reportType,
      MemberEntity memberEntity,
      MessageEntity messageEntity
  ) {
    return ReportEntity.builder()
        .reportType(reportType)
        .memberEntity(memberEntity)
        .messageEntity(messageEntity)
        .build();
  }
}
