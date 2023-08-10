package com.rocket.birthday.model.message;

import static com.rocket.birthday.common.constant.BirthdayConstants.SEOUL_ZONEID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "messages_deleted")
@Entity
public class MessageDeleted {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String content;

  @Column(name = "from_member_id")
  private Long from;

  @Column(name = "to_member_id")
  private Long to;

  @Column(name = "color_code")
  private String colorCode;

  @Column(name = "open_date")
  private ZonedDateTime openDate;

  @Column(name = "deleted_at")
  private ZonedDateTime deletedAt;

  @PrePersist
  public void onPrePersist() {
    this.deletedAt = ZonedDateTime.now(SEOUL_ZONEID);
  }
}
