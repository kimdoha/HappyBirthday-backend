package com.rocket.birthday.repository.message;

import static com.rocket.birthday.common.constant.BirthdayConstants.SEOUL_ZONEID;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rocket.birthday.model.message.Message;
import com.rocket.birthday.model.message.QMessage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepositoryCustom{

  private final JPAQueryFactory queryFactory;

  @Override
  public Slice<Message> findSliceByOpenDate(Pageable page) {
    List<Message> messages = queryFactory.select(QMessage.message)
        .from(QMessage.message)
        .where(QMessage.message.openDate.between(
            ZonedDateTime.of(LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0)), SEOUL_ZONEID ),
            ZonedDateTime.of(LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 59, 59)), SEOUL_ZONEID)
        ))
        .offset((long) page.getPageNumber() * page.getPageSize())
        .limit(page.getPageSize() + 1)
        .fetch();

    return new SliceImpl<>(
        messages.stream().limit(page.getPageSize()).toList(),
        page,
        messages.size() > page.getPageSize()
    );
  }
}
