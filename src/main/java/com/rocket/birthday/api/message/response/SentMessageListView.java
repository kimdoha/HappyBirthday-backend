package com.rocket.birthday.api.message.response;

import com.rocket.birthday.common.dto.OffsetPagingInfoView;
import com.rocket.birthday.common.dto.OffsetPagingView;
import com.rocket.birthday.model.message.MessageEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Pageable;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SentMessageListView extends OffsetPagingView {
  private List<MessageInfoView> messages;
  public static SentMessageListView of(
      List<MessageEntity> messageEntities,
      Pageable page
  ) {
    return SentMessageListView.builder()
        .messages(
            messageEntities.stream().map(messageEntity ->
                MessageInfoView.builder()
                    .id(messageEntity.getId())
                    .content(messageEntity.getContent())
                    .to(messageEntity.getTo() == null ? null : messageEntity.getTo().getNickname())
                    .createdAt(messageEntity.getCreatedAt())
                    .build()
            ).toList()
        )
        .page(
            OffsetPagingInfoView.of(
                page.getPageNumber() * page.getPageSize(),
                page.getPageSize()
            ))
        .build();
  }
}
