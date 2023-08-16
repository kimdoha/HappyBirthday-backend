package com.rocket.birthday.repository.message;

import com.rocket.birthday.model.message.MessageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MessageRepositoryCustom {
  Slice<MessageEntity> findSliceByTodayOpenDate(Pageable page);
  Slice<MessageEntity> findSliceOpenDateAfterToday(Long memberId, Pageable page);
}
