package com.rocket.birthday.service.message;

import com.rocket.birthday.api.message.dto.request.PostMessageRequest;
import com.rocket.birthday.api.message.dto.response.MessageInfoView;
import com.rocket.birthday.api.message.mapper.MessageMapper;
import com.rocket.birthday.common.exception.custom.message.MessageNotFoundException;
import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.message.Message;
import com.rocket.birthday.repository.message.MessageRepository;
import com.rocket.birthday.service.member.MemberService;
import jakarta.security.auth.message.MessageInfo;
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
}
