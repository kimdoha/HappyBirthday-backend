package com.rocket.birthday.repository.message;

import com.rocket.birthday.model.message.Message;
import java.time.ZonedDateTime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MessageRepositoryCustom {
  Slice<Message> findSliceByOpenDate(Pageable page);
}
