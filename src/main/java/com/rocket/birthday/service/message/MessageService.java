package com.rocket.birthday.service.message;

import static com.rocket.birthday.common.exception.enums.BaseErrorCode.*;

import com.rocket.birthday.api.message.dto.request.PostMessageRequest;
import com.rocket.birthday.api.message.dto.request.UpdateMessageRequest;
import com.rocket.birthday.api.message.dto.response.MessageExistInfoView;
import com.rocket.birthday.api.message.dto.response.MessageInfoView;
import com.rocket.birthday.api.message.mapper.MessageMapper;
import com.rocket.birthday.common.exception.custom.message.InvalidMessageRequestException;
import com.rocket.birthday.common.exception.custom.message.MessageNotFoundException;
import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.message.Message;
import com.rocket.birthday.model.message.MessageDeleted;
import com.rocket.birthday.repository.message.MessageDeletedRepository;
import com.rocket.birthday.repository.message.MessageRepository;
import com.rocket.birthday.service.member.MemberService;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {
  private final MemberService memberService;
  private final MessageRepository messageRepository;
  private final MessageDeletedRepository messageDeletedRepository;
  private final MessageMapper messageMapper;

  @Transactional
  public MessageInfoView createMessage(Long fromMemberId, PostMessageRequest postMessageRequest) {
    Member fromMember = memberService.findOne(fromMemberId);
    Boolean receiverExist =  postMessageRequest.getReceiverExist();
    Member toMember = receiverExist ? memberService.findOne(postMessageRequest.getReceiverId()) : new Member();
    Message message = messageMapper.toEntity(postMessageRequest, toMember, fromMember, receiverExist);

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
    return messageRepository.findById( messageId )
        .orElseThrow(() -> MessageNotFoundException.EXCEPTION);
  }
}
