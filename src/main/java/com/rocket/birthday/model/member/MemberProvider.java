package com.rocket.birthday.model.member;

import com.rocket.birthday.model.member.vo.ProviderType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "members_provider")
@Entity
public class MemberProvider {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(mappedBy = "memberProvider")
  private Member member;

  @OneToOne(mappedBy = "memberProvider")
  private MemberDeleted memberDeleted;

  @Column(name = "provider_id")
  private String providerId;

  @Column(name = "provider_type")
  @Enumerated(EnumType.STRING)
  private ProviderType providerType;
}
