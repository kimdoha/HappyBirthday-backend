package com.rocket.birthday.api.message.response;

import com.rocket.birthday.model.message.Message;
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
  private List<MessageInfoView> messages;
  private PageInfo page;

  @Getter
  @Builder
  @AllArgsConstructor(access = AccessLevel.PROTECTED)
  @NoArgsConstructor
  public static class PageInfo {
    private Integer offset;
    private Integer limit;
  }

  public static TodayMessageListView of(
      List<Message> messages,
      Pageable page
  ) {
    return TodayMessageListView.builder()
        .messages(
            messages.stream().map((message) -> MessageInfoView.from(message)).toList())
        .page(
            PageInfo.builder()
                .offset(page.getPageNumber() * page.getPageSize())
                .limit(page.getPageSize())
                .build())
        .build();
  }
}
