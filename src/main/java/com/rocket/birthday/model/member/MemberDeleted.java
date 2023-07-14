package com.rocket.birthday.model.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(
    name = "members_deleted",
    uniqueConstraints = {
        @UniqueConstraint( name = "unique_member_email", columnNames = { "email "}),
        @UniqueConstraint( name = "unique_member_name", columnNames = { "name "}),
    }
)
@Entity
public class MemberDeleted {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
  private MemberProvider memberProvider;

  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String name;
  @Column(name = "profile_image_url")
  private String profileImageUrl;

  @Column(nullable = false)
  private LocalDate birthday;

  @CreatedDate
  @Column(name = "deleted_at", updatable = false)
  private ZonedDateTime deletedAt;
}
