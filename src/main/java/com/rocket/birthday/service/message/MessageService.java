package com.rocket.birthday.service.message;

import static com.rocket.birthday.common.constant.BirthdayConstants.SEOUL_ZONEID;
import static com.rocket.birthday.common.exception.enums.BaseErrorCode.*;

import com.rocket.birthday.api.message.request.PostMessageRequest;
import com.rocket.birthday.api.message.request.UpdateMessageRequest;
import com.rocket.birthday.api.message.response.MessageExistInfoView;
import com.rocket.birthday.api.message.response.MessageDetailInfoView;
import com.rocket.birthday.api.message.response.TodayMessageListView;
import com.rocket.birthday.common.exception.custom.member.MemberNotFoundException;
import com.rocket.birthday.repository.member.MemberRepository;
import com.rocket.birthday.service.message.factory.MessageFactory;
import com.rocket.birthday.service.message.mapper.MessageAssembler;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {
  private final MemberRepository memberRepository;
  private final MessageRepository messageRepository;
  private final MessageDeletedRepository messageDeletedRepository;
  private final MessageAssembler messageAssembler;
  private final MessageFactory messageFactory;

  @Transactional
  public MessageDetailInfoView createMessage(
      Long senderId,
      PostMessageRequest postMessageRequest
  ) {
    Member sender = memberRepository.findById(senderId)
        .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

    Member receiver = null;
    if(postMessageRequest.getMessageType().equals(MessageType.DIRECT)){
      receiver = memberRepository.findById(postMessageRequest.getReceiverId())
          .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

//      if(sender.getId().equals(receiver.getId())){
//        throw new InvalidMessageRequestException(NOT_AVAILABLE_MESSAGE_CREATE);
//      }
    }

    if(postMessageRequest.getOpenDate().isBefore(ZonedDateTime.now(SEOUL_ZONEID))) {
      throw new InvalidMessageRequestException(NOT_AVAILABLE_MESSAGE_CREATE_BEFORE_NOW);
    }

    Message message = messageFactory.create(
        postMessageRequest.getMessageType(),
        postMessageRequest.toCommand(sender, receiver));

    if(message == null) {
      throw new InvalidMessageRequestException(INVALID_MESSAGE_CREATE_TYPE);
    }

    Message result = messageRepository.save(message);
    return MessageDetailInfoView.from(result);
  }

  @Transactional(readOnly = true)
  public TodayMessageListView getTodayAllMessages(Pageable page) {
    Slice<Message> messages = messageRepository.findSliceByOpenDate(page);

    if(!messages.hasContent()) {
      throw new MessageNotFoundException(TODAY_MESSAGE_NOT_FOUND);
    }

    return TodayMessageListView.of(messages.stream().toList(), page);
  }

  @Transactional(readOnly = true)
  public MessageDetailInfoView getMessageInfo(Long id) {
    Message message = findMessageById(id);

    return MessageDetailInfoView.from(message);
  }

  @Transactional
  public MessageDetailInfoView updateMessage(
      Long messageId,
      Long memberId,
      UpdateMessageRequest updateMessageRequest
  ) {
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
    return MessageDetailInfoView.from(result);
  }

  @Transactional
  public MessageExistInfoView deleteMessage(
      Long messageId,
      Long memberId
  ) {
    Message message = findMessageById(messageId);

    if(!message.getFrom().getId().equals(memberId)) {
      throw new InvalidMessageRequestException(NOT_AVAILABLE_MESSAGE_DELETE);
    }

    MessageDeleted deletedMessage = messageAssembler.toMessageDeletedEntity(message);
    messageDeletedRepository.save(deletedMessage);
    messageRepository.delete(message);

    return MessageExistInfoView.of(messageId, false);
  }

  private Message findMessageById(Long messageId) {
    return messageRepository.findById(messageId)
        .orElseThrow(() -> new MessageNotFoundException(MESSAGE_NOT_FOUND));
  }
}
