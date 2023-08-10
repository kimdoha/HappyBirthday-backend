package com.rocket.birthday.model.auth;

import static com.rocket.birthday.common.constant.BirthdayConstants.SEOUL_ZONEID;

import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.member.vo.ProviderType;
import jakarta.persistence.*;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_providers")
@Entity
public class MemberProvider {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", referencedColumnName = "id")
  private Member member;

  @Column(name = "oid")
  private String oid;

  @Column(name = "provider")
  @Enumerated(EnumType.STRING)
  private ProviderType provider;

  @Column(name = "created_at", updatable = false)
  private ZonedDateTime createdAt;

  @PrePersist
  public void onPrePersist() {
    this.createdAt = ZonedDateTime.now(SEOUL_ZONEID);
  }
}
