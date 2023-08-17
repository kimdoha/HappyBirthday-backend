package com.rocket.birthday.api.message.response;

import com.rocket.birthday.common.dto.OffsetPagingInfoView;
import com.rocket.birthday.model.message.MessageEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceivedMessageListView {
  private List<ReceivedMessageInfoView> messages;
  private OffsetPagingInfoView page;

  public static ReceivedMessageListView of(
      List<MessageEntity> messageEntities,
      Pageable page
  ) {
   return ReceivedMessageListView
       .builder()
       .messages(
           messageEntities.stream().map(
               messageEntity ->
                   ReceivedMessageInfoView.from(messageEntity)
           ).toList()
       )
       .page(OffsetPagingInfoView.of(page.getPageSize() * page.getPageNumber(), page.getPageSize()))
       .build();
  }
}
