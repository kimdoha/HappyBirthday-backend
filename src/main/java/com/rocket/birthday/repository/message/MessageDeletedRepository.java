package com.rocket.birthday.repository.message;


import com.rocket.birthday.model.message.MessageDeleted;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDeletedRepository
    extends JpaRepository<MessageDeleted, Long> {

}
