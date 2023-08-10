package com.rocket.birthday.model.member;

import static com.rocket.birthday.common.constant.BirthdayConstants.SEOUL_ZONEID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "members_deleted")
@Entity
public class MemberDeleted {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false, unique = true)
  private String nickname;

  @Column(name = "profile_image_url")
  private String profileImageUrl;

  @Column(nullable = false)
  private LocalDate birthday;

  @Column(name = "deleted_at", updatable = false)
  private ZonedDateTime deletedAt;

  @PrePersist
  public void onPrePersist() {
    this.deletedAt = ZonedDateTime.now(SEOUL_ZONEID);
  }
}