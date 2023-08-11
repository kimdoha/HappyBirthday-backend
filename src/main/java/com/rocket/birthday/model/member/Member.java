package com.rocket.birthday.model.member;

import static com.rocket.birthday.common.constant.BirthdayConstants.SEOUL_ZONEID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "members")
@Entity
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false, unique = true)
  private String nickname;

  @Column(name = "profile_image_url")
  private String profileImageUrl;

  private ZonedDateTime birthday;

  @Column(name = "created_at", updatable = false)
  private ZonedDateTime createdAt;

  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  @PrePersist
  public void onPrePersist() {
    this.createdAt = ZonedDateTime.now(SEOUL_ZONEID);
    this.updatedAt = ZonedDateTime.now(SEOUL_ZONEID);
  }

  @PreUpdate
  public void onPreUpdate() {
    this.updatedAt = ZonedDateTime.now(SEOUL_ZONEID);
  }

  public Member update(String _nickname, String _profileImageUrl, ZonedDateTime _birthday) {
    this.nickname = _nickname;
    this.profileImageUrl = _profileImageUrl;
    this.birthday = _birthday;
    this.updatedAt = ZonedDateTime.now(SEOUL_ZONEID);
    return this;
  }
}
