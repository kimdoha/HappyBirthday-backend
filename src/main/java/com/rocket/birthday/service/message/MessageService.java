package com.rocket.birthday.service.message;

import com.rocket.birthday.api.message.dto.request.PostMessageRequest;
import com.rocket.birthday.api.message.dto.response.MessageInfoView;
import com.rocket.birthday.api.message.mapper.MessageMapper;
import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.message.Message;
import com.rocket.birthday.repository.message.MessageRepository;
import com.rocket.birthday.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
  private final MemberService memberService;
  private final MessageRepository messageRepository;
  private final MessageMapper messageMapper;

  public MessageInfoView createMessage(Long memberId, PostMessageRequest postMessageRequest) {

    Member toMember = memberService.findOneByNickname(postMessageRequest.getNickname());
    Member fromMember = memberService.findOne(memberId);

    Message message = messageMapper.toEntity( postMessageRequest, toMember, fromMember);
    Message result = messageRepository.save(message);

    return messageMapper.toMessageInfoView(result);
  }
}
