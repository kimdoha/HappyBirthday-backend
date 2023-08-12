package com.rocket.birthday.service.report;

import static com.rocket.birthday.common.exception.enums.BaseErrorCode.MEMBER_NOT_FOUND;
import static com.rocket.birthday.common.exception.enums.BaseErrorCode.MESSAGE_NOT_FOUND;

import com.rocket.birthday.api.report.request.CreateMessageReportRequest;
import com.rocket.birthday.api.report.response.MessageReportInfoView;
import com.rocket.birthday.common.exception.custom.member.MemberNotFoundException;
import com.rocket.birthday.common.exception.custom.message.MessageNotFoundException;
import com.rocket.birthday.model.member.MemberEntity;
import com.rocket.birthday.model.message.MessageEntity;
import com.rocket.birthday.model.report.ReportEntity;
import com.rocket.birthday.repository.member.MemberRepository;
import com.rocket.birthday.repository.message.MessageRepository;
import com.rocket.birthday.repository.report.ReportRepository;
import com.rocket.birthday.service.report.mapper.ReportAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReportService {
  private final MemberRepository memberRepository;
  private final MessageRepository messageRepository;
  private final ReportRepository reportRepository;
  private final ReportAssembler reportAssembler;

  @Transactional
  public MessageReportInfoView createMessageReport(
      CreateMessageReportRequest createMessageReportRequest,
      Long memberId
  ) {
    MemberEntity memberEntity = memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

    MessageEntity messageEntity = messageRepository.findById(
            createMessageReportRequest.getMessageId() )
        .orElseThrow(() -> new MessageNotFoundException(MESSAGE_NOT_FOUND));

    ReportEntity reportEntity = reportAssembler.assemble(
        createMessageReportRequest.getReportType(),
        memberEntity,
        messageEntity
    );

    ReportEntity resultEntity = reportRepository.save(reportEntity);

    return MessageReportInfoView.from(resultEntity);
  }
}
