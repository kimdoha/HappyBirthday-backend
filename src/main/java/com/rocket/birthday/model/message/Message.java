package com.rocket.birthday.model.message;

import com.rocket.birthday.model.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
@Entity
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @Size(min = 1, max = 5000)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "from_member_id", referencedColumnName = "id")
  private Member from;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "to_member_id", referencedColumnName = "id")
  private Member to;

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
    this.createdAt = ZonedDateTime.now();
    this.updatedAt = ZonedDateTime.now();
  }

  @PreUpdate
  public void onPreUpdate() {
    this.updatedAt = ZonedDateTime.now();
  }

  public Message update(String _content, String _colorCode, ZonedDateTime _openDate) {
    this.content = _content;
    this.colorCode = _colorCode;
    this.openDate = _openDate;
    this.updatedAt = ZonedDateTime.now();
    return this;
  }
}
