package com.rocket.birthday.model.message;

import static com.rocket.birthday.common.constant.BirthdayConstants.SEOUL_ZONEID;

import com.rocket.birthday.model.member.MemberEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
@Entity
public class MessageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @Size(min = 1, max = 5000)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "from_member_id", referencedColumnName = "id")
  private MemberEntity from;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "to_member_id", referencedColumnName = "id")
  private MemberEntity to;

  @Column(name = "color_code")
  private String colorCode;

  @Column(name = "open_date")
  private ZonedDateTime openDate;

  @Column(name = "created_at")
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

  public MessageEntity update(String _content, String _colorCode, ZonedDateTime _openDate) {
    this.content = _content;
    this.colorCode = _colorCode;
    this.openDate = _openDate;
    this.updatedAt = ZonedDateTime.now();
    return this;
  }
}
