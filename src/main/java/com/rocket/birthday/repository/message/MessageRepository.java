package com.rocket.birthday.repository.message;

import com.rocket.birthday.model.message.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository
    extends JpaRepository<MessageEntity, Long>, MessageRepositoryCustom {

}
