package com.rocket.birthday.model.auth;

import static com.rocket.birthday.common.constant.BirthdayConstants.SEOUL_ZONEID;

import com.rocket.birthday.model.member.vo.ProviderType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_providers_deleted")
@Entity
public class MemberProviderDeleted {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String oid;
  private ProviderType provider;
  private Long memberId;

  @Column(name = "deleted_at", updatable = false)
  private ZonedDateTime deletedAt;

  @PrePersist
  public void onPrePersist() {
    this.deletedAt = ZonedDateTime.now(SEOUL_ZONEID);
  }
}
