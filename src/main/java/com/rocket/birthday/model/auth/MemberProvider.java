package com.rocket.birthday.model.auth;

import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.member.vo.ProviderType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

  @CreatedDate
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;
}
