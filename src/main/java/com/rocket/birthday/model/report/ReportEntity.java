package com.rocket.birthday.model.report;

import static com.rocket.birthday.common.constant.BirthdayConstants.SEOUL_ZONEID;

import com.rocket.birthday.model.member.MemberEntity;
import com.rocket.birthday.model.message.MessageEntity;
import com.rocket.birthday.model.report.vo.ReportType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reports")
@Entity
public class ReportEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "report_type", nullable = false)
  private ReportType reportType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "message_id", referencedColumnName = "id")
  private MessageEntity messageEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", referencedColumnName = "id")
  private MemberEntity memberEntity;

  private ZonedDateTime createdAt;

  @PrePersist
  public void onPrePersist() {
    this.createdAt = ZonedDateTime.now(SEOUL_ZONEID);
  }
}
