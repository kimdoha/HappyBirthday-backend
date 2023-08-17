package com.rocket.birthday.api.message.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyMessageInfoView {
  private Long modifiableMessages;
  private Long sentMessages;
  private Long receivedMessages;

  public static MyMessageInfoView of(
      Long modifiableMessageCount,
      Long sentMessageCount,
      Long receivedMessageCount
  ) {
    return MyMessageInfoView.builder()
        .modifiableMessages(modifiableMessageCount)
        .sentMessages(sentMessageCount)
        .receivedMessages(receivedMessageCount)
        .build();
  }
}
