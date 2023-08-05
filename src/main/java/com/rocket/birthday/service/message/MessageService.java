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


  public MessageInfoView createMessage(Long fromMemberId, Long toMemberId, PostMessageRequest postMessageRequest) {

    Member toMember = memberService.findOne(toMemberId);
    Member fromMember = memberService.findOne(fromMemberId);

//    if(toMember.getId().equals(fromMember.getId())) {
//      throw new
//    }

    Message message = messageMapper.toEntity( postMessageRequest, toMember, fromMember);
    System.out.println(message);
    Message result = messageRepository.save(message);
    System.out.println(result);
    return messageMapper.toMessageInfoView(result);
  }
}
