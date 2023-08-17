package com.rocket.birthday.repository.message;

import com.rocket.birthday.api.message.response.MyMessageInfoView;
import com.rocket.birthday.model.message.MessageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MessageRepositoryCustom {
  Slice<MessageEntity> findOpenDateIsTodaySlice(Pageable page);
  Slice<MessageEntity> findOpenDateIsAfterTodaySlice(Long memberId, Pageable page);
  Slice<MessageEntity> findSentMessageSlice(Long memberId, Pageable page);
  Slice<MessageEntity> findReceivedMessageSlice(Long memberId, Pageable page);
  MyMessageInfoView findMyMessageInfo(Long memberId);
}
