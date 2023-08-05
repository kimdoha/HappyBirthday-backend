package com.rocket.birthday.service.message;

import static com.rocket.birthday.common.exception.enums.BaseErrorCode.NOT_AVAILABLE_MESSAGE_UPDATE;
import static com.rocket.birthday.common.exception.enums.BaseErrorCode.NOT_AVAILABLE_MESSAGE_UPDATE_AFTER_DATE;

import com.rocket.birthday.api.message.dto.request.PostMessageRequest;
import com.rocket.birthday.api.message.dto.request.UpdateMessageRequest;
import com.rocket.birthday.api.message.dto.response.MessageInfoView;
import com.rocket.birthday.api.message.mapper.MessageMapper;
import com.rocket.birthday.common.exception.custom.message.InvalidMessageRequestException;
import com.rocket.birthday.common.exception.custom.message.MessageNotFoundException;
import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.message.Message;
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
    Message result = messageRepository.findById(id)
        .orElseThrow(() -> MessageNotFoundException.EXCEPTION );

    return messageMapper.toMessageInfoView(result);
  }

  @Transactional
  public MessageInfoView updateMessage(Long messageId, Long memberId, UpdateMessageRequest updateMessageRequest) {
    Message message = messageRepository.findById(messageId)
        .orElseThrow(() -> MessageNotFoundException.EXCEPTION);

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
}
