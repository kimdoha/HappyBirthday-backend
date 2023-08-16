package com.rocket.birthday.api.message.response;

import com.rocket.birthday.common.dto.OffsetPagingInfoView;
import com.rocket.birthday.model.message.MessageEntity;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class ModifiedMessageListView {
  private List<MessageInfoView> messages;
  private OffsetPagingInfoView page;

  public static ModifiedMessageListView of(
      List<MessageEntity> messageEntities,
      Pageable page
  ) {
    return ModifiedMessageListView.builder()
        .messages(
            messageEntities.stream().map((messageEntity) ->
              MessageInfoView.builder()
                  .id(messageEntity.getId())
                  .content(messageEntity.getContent())
                  .to(messageEntity.getTo() == null ? null : messageEntity.getTo().getNickname())
                  .createdAt(messageEntity.getCreatedAt())
                  .build()
            ).toList())
        .page(
            OffsetPagingInfoView.of(
                page.getPageNumber() * page.getPageSize(),
                page.getPageSize()
            ))
        .build();
  }
}
