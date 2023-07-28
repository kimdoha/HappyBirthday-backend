package com.rocket.birthday.model.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

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

  @CreatedDate
  @Column(name = "deleted_at", updatable = false)
  private ZonedDateTime deletedAt;
}