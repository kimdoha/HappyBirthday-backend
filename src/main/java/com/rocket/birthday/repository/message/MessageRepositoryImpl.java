package com.rocket.birthday.repository.message;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rocket.birthday.model.message.Message;
import com.rocket.birthday.model.message.QMessage;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepositoryCustom{

  private final JPAQueryFactory queryFactory;

  @Override
  public Slice<Message> findSliceByOpenDate(Pageable page) {
    var now = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);

    List<Message> messages = queryFactory.select(QMessage.message)
        .from(QMessage.message)
        .where(QMessage.message.openDate.between(now, now.plusDays(1)))
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
