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
public class TodayMessageListView {
  private List<TodayMessageInfoView> messages;
  private OffsetPagingInfoView page;

  public static TodayMessageListView of(
      List<MessageEntity> messageEntities,
      Pageable page
  ) {
    return TodayMessageListView.builder()
        .messages(
            messageEntities.stream().map((message) -> TodayMessageInfoView.from(message)).toList())
        .page( OffsetPagingInfoView.of( page.getPageNumber() * page.getPageSize(), page.getPageSize() ) )
        .build();
  }
}
