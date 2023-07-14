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

@Table(name = "members_provider")
@Entity
public class MemberProvider {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  private Member member;

  @Column(name = "provider_id")
  private String providerId;

  @Column(name = "provider_type")
  @Enumerated(EnumType.STRING)
  private ProviderType providerType;
}
