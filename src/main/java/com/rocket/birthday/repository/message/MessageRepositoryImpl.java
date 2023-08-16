package com.rocket.birthday.repository.message;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rocket.birthday.model.message.MessageEntity;
import com.rocket.birthday.model.message.QMessageEntity;
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
  public Slice<MessageEntity> findSliceByTodayOpenDate(Pageable page) {
    var now = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);

    List<MessageEntity> messageEntities = queryFactory.select(QMessageEntity.messageEntity)
        .from(QMessageEntity.messageEntity)
        .where(QMessageEntity.messageEntity.openDate.between(now, now.plusDays(1)))
        .offset((long) page.getPageNumber() * page.getPageSize())
        .limit(page.getPageSize() + 1)
        .fetch();

    return new SliceImpl<>(
        messageEntities.stream().limit(page.getPageSize()).toList(),
        page,
        messageEntities.size() > page.getPageSize()
    );
  }

  @Override
  public Slice<MessageEntity> findSliceOpenDateAfterToday(Long memberId, Pageable page) {
    var now = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);

    List<MessageEntity> messageEntities = queryFactory.select(QMessageEntity.messageEntity)
        .from(QMessageEntity.messageEntity)
        .where(QMessageEntity.messageEntity.from.id.eq(memberId)
            .and(QMessageEntity.messageEntity.openDate.after(now)))
        .offset((long) page.getPageNumber() * page.getPageSize())
        .limit(page.getPageSize() + 1)
        .fetch();

    return new SliceImpl<>(
        messageEntities.stream().limit(page.getPageSize()).toList(),
        page,
        messageEntities.size() > page.getPageSize()
    );
  }
}
