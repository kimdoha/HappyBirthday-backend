package com.rocket.birthday.model.message;

import com.rocket.birthday.model.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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
  private LocalDateTime openDate;

  @CreatedDate
  @Column(name = "deleted_at", updatable = false)
  private LocalDateTime deletedAt;
}
