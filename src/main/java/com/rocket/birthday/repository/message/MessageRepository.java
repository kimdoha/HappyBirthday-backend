package com.rocket.birthday.repository.message;

import com.rocket.birthday.model.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository
    extends JpaRepository<Message, Long>, MessageRepositoryCustom {

}
