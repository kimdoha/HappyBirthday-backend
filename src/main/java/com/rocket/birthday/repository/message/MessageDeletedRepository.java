package com.rocket.birthday.repository.message;


import com.rocket.birthday.model.message.MessageDeleteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDeletedRepository
    extends JpaRepository<MessageDeleteEntity, Long> {

}
