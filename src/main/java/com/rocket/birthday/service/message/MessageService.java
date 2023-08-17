package com.rocket.birthday.service.message;

import static com.rocket.birthday.common.constant.BirthdayConstants.SEOUL_ZONEID;
import static com.rocket.birthday.common.exception.enums.BaseErrorCode.*;

import com.rocket.birthday.api.message.request.PostMessageRequest;
import com.rocket.birthday.api.message.request.UpdateMessageRequest;
import com.rocket.birthday.api.message.response.MessageExistInfoView;
import com.rocket.birthday.api.message.response.MessageDetailInfoView;
import com.rocket.birthday.api.message.response.ModifiedMessageListView;
import com.rocket.birthday.api.message.response.ReceivedMessageListView;
import com.rocket.birthday.api.message.response.SentMessageListView;
import com.rocket.birthday.api.message.response.TodayMessageListView;
import com.rocket.birthday.common.exception.custom.member.MemberNotFoundException;
import com.rocket.birthday.model.member.MemberEntity;
import com.rocket.birthday.model.message.MessageEntity;
import com.rocket.birthday.repository.member.MemberRepository;
import com.rocket.birthday.service.message.factory.MessageFactory;
import com.rocket.birthday.service.message.mapper.MessageAssembler;
import com.rocket.birthday.common.exception.custom.message.InvalidMessageRequestException;
import com.rocket.birthday.common.exception.custom.message.MessageNotFoundException;
import com.rocket.birthday.model.message.MessageDeleteEntity;
import com.rocket.birthday.repository.message.MessageDeletedRepository;
import com.rocket.birthday.repository.message.MessageRepository;
import com.rocket.birthday.service.message.vo.MessageType;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
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
    MemberEntity sender = memberRepository.findById(senderId)
        .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

    MemberEntity receiver = null;
    if(postMessageRequest.getMessageType().equals(MessageType.DIRECT)){
      receiver = memberRepository.findById(postMessageRequest.getReceiverId())
          .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

      if(sender.getId().equals(receiver.getId())){
        throw new InvalidMessageRequestException(NOT_AVAILABLE_MESSAGE_CREATE);
      }
    }

    if(postMessageRequest.getOpenDate().isBefore(ZonedDateTime.now(SEOUL_ZONEID))) {
      throw new InvalidMessageRequestException(NOT_AVAILABLE_MESSAGE_CREATE_BEFORE_NOW);
    }

    MessageEntity messageEntity = messageFactory.create(
        postMessageRequest.getMessageType(),
        postMessageRequest.toCommand(sender, receiver));

    if(messageEntity == null) {
      throw new InvalidMessageRequestException(INVALID_MESSAGE_CREATE_TYPE);
    }

    MessageEntity result = messageRepository.save( messageEntity );
    return MessageDetailInfoView.from(result);
  }

  @Transactional(readOnly = true)
  public TodayMessageListView getTodayAllMessages(Pageable page) {
    Slice<MessageEntity> messages = messageRepository.findOpenDateIsTodaySlice(page);

    if(!messages.hasContent()) {
      throw new MessageNotFoundException(TODAY_MESSAGE_NOT_FOUND);
    }

    return TodayMessageListView.of(messages.stream().toList(), page);
  }

  @Transactional(readOnly = true)
  public ModifiedMessageListView getModifiableAllMessages(Long memberId, Pageable page) {
    memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

    Slice<MessageEntity> messageEntities = messageRepository.findOpenDateIsAfterTodaySlice(memberId, page);

    if(!messageEntities.hasContent()) {
      throw new MessageNotFoundException(MODIFIED_MESSAGE_NOT_FOUND);
    }

    return  ModifiedMessageListView.of(messageEntities.getContent(), page);
  }

  @Transactional(readOnly = true)
  public SentMessageListView getSentAllMessages(Long memberId, Pageable page) {
    memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

    Slice<MessageEntity> messageEntities = messageRepository.findSentMessageSlice(memberId, page);

    if(!messageEntities.hasContent()) {
      throw new MessageNotFoundException(SENT_MESSAGE_NOT_FOUND);
    }

    return SentMessageListView.of(messageEntities.getContent(), page);
  }

  @Transactional(readOnly = true)
  public ReceivedMessageListView getReceivedAllMessages(Long memberId, Pageable page) {
    memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

    Slice<MessageEntity> messageEntities = messageRepository.findReceivedMessageSlice(memberId, page);

    if(!messageEntities.hasContent()) {
      throw new MessageNotFoundException(RECEIVED_MESSAGE_NOT_FOUND);
    }

    return ReceivedMessageListView.of(messageEntities.getContent(), page);
  }


  @Transactional(readOnly = true)
  public MessageDetailInfoView getMessageInfo(Long id) {
    MessageEntity messageEntity = findMessageById(id);

    return MessageDetailInfoView.from( messageEntity );
  }

  @Transactional
  public MessageDetailInfoView updateMessage(
      Long messageId,
      Long memberId,
      UpdateMessageRequest updateMessageRequest
  ) {
    MessageEntity messageEntity = findMessageById(messageId);

    if(!messageEntity.getFrom().getId().equals(memberId)) {
      throw new InvalidMessageRequestException(NOT_AVAILABLE_MESSAGE_UPDATE);
    }

    if(!ZonedDateTime.now().isBefore( messageEntity.getOpenDate())) {
      throw new InvalidMessageRequestException(NOT_AVAILABLE_MESSAGE_UPDATE_AFTER_DATE);
    }

    MessageEntity updatedMessageEntity = messageEntity.update(
        updateMessageRequest.getContent(),
        updateMessageRequest.getColorCode(),
        ZonedDateTime.parse(updateMessageRequest.getOpenDate())
    );

    MessageEntity result = messageRepository.save( updatedMessageEntity );
    return MessageDetailInfoView.from(result);
  }

  @Transactional
  public MessageExistInfoView deleteMessage(
      Long messageId,
      Long memberId
  ) {
    MessageEntity messageEntity = findMessageById(messageId);

    if(!messageEntity.getFrom().getId().equals(memberId)) {
      throw new InvalidMessageRequestException(NOT_AVAILABLE_MESSAGE_DELETE);
    }

    MessageDeleteEntity deletedMessage = messageAssembler.toMessageDeletedEntity( messageEntity );
    messageDeletedRepository.save(deletedMessage);
    messageRepository.delete( messageEntity );

    return MessageExistInfoView.of(messageId, false);
  }

  private MessageEntity findMessageById(Long messageId) {
    return messageRepository.findById(messageId)
        .orElseThrow(() -> new MessageNotFoundException(MESSAGE_NOT_FOUND));
  }
}
