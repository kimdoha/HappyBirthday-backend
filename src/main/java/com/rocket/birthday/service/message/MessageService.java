package com.rocket.birthday.service.message;

import static com.rocket.birthday.common.exception.enums.BaseErrorCode.*;

import com.rocket.birthday.api.message.request.PostMessageRequest;
import com.rocket.birthday.api.message.request.UpdateMessageRequest;
import com.rocket.birthday.api.message.response.MessageExistInfoView;
import com.rocket.birthday.api.message.response.MessageInfoView;
import com.rocket.birthday.common.exception.custom.member.MemberNotFoundException;
import com.rocket.birthday.repository.member.MemberRepository;
import com.rocket.birthday.service.message.factory.MessageFactory;
import com.rocket.birthday.service.message.mapper.MessageMapper;
import com.rocket.birthday.common.exception.custom.message.InvalidMessageRequestException;
import com.rocket.birthday.common.exception.custom.message.MessageNotFoundException;
import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.message.Message;
import com.rocket.birthday.model.message.MessageDeleted;
import com.rocket.birthday.repository.message.MessageDeletedRepository;
import com.rocket.birthday.repository.message.MessageRepository;
import com.rocket.birthday.service.message.vo.MessageType;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {
  private final MemberRepository memberRepository;
  private final MessageRepository messageRepository;
  private final MessageDeletedRepository messageDeletedRepository;
  private final MessageMapper messageMapper;
  private final MessageFactory messageFactory;

  @Transactional
  public MessageInfoView createMessage(
      Long senderId,
      PostMessageRequest postMessageRequest
  ) {
    Member sender = memberRepository.findById(senderId)
        .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

    Member receiver = null;
    if(postMessageRequest.getMessageType().equals(MessageType.DIRECT)){
      receiver = memberRepository.findById(postMessageRequest.getReceiverId())
          .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));
    }

    Message message = messageFactory.create(
        postMessageRequest.getMessageType(),
        postMessageRequest.toCommand(sender, receiver));

    //TODO 1. message == null -> MessageType ERROR
    //TODO 2. 본인에게 작성 시 -> Business ERROR
    Message result = messageRepository.save(message);
    return messageMapper.toMessageInfoView(result);
  }

  @Transactional(readOnly = true)
  public MessageInfoView getMessageInfo(Long id) {
    Message message = findMessageById(id);

    return messageMapper.toMessageInfoView(message);
  }

  @Transactional
  public MessageInfoView updateMessage(Long messageId, Long memberId, UpdateMessageRequest updateMessageRequest) {
    Message message = findMessageById(messageId);

    if(!message.getFrom().getId().equals(memberId)) {
      throw new InvalidMessageRequestException(NOT_AVAILABLE_MESSAGE_UPDATE);
    }

    if(!ZonedDateTime.now().isBefore(message.getOpenDate())) {
      throw new InvalidMessageRequestException(NOT_AVAILABLE_MESSAGE_UPDATE_AFTER_DATE);
    }

    Message updatedMessage = message.update(
        updateMessageRequest.getContent(),
        updateMessageRequest.getColorCode(),
        ZonedDateTime.parse(updateMessageRequest.getOpenDate())
    );

    Message result = messageRepository.save(updatedMessage);
    return messageMapper.toMessageInfoView(result);
  }

  @Transactional
  public MessageExistInfoView deleteMessage(Long messageId, Long memberId) {
    Message message = findMessageById(messageId);

    if(!message.getFrom().getId().equals(memberId)) {
      throw new InvalidMessageRequestException(NOT_AVAILABLE_MESSAGE_DELETE);
    }

    MessageDeleted deletedMessage = messageMapper.toMessageDeletedEntity(message);
    messageDeletedRepository.save(deletedMessage);
    messageRepository.delete(message);

    return messageMapper.toMessageExistInfoView(messageId);
  }

  private Message findMessageById(Long messageId) {
    return messageRepository.findById(messageId)
        .orElseThrow(() -> MessageNotFoundException.EXCEPTION);
  }
}
