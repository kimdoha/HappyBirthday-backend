package com.rocket.birthday.api.message;

import com.rocket.birthday.api.message.request.PostMessageRequest;
import com.rocket.birthday.api.message.request.UpdateMessageRequest;
import com.rocket.birthday.api.message.response.MessageExistInfoView;
import com.rocket.birthday.api.message.response.MessageInfoView;
import com.rocket.birthday.api.message.response.TodayMessageListView;
import com.rocket.birthday.service.member.dto.MemberDetails;
import com.rocket.birthday.service.message.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class MessageController {
  private final MessageService messageService;
  @PostMapping("/message")
  public MessageInfoView postMessage(
      @RequestBody PostMessageRequest postMessageRequest,
      @AuthenticationPrincipal MemberDetails member
  ) {
    return messageService.createMessage(
        member.getMemberId(),
        postMessageRequest
    );
  }

  @GetMapping("/messages")
  public TodayMessageListView getTodayMessages(
      @RequestParam Integer offset,
      @RequestParam Integer limit
  ) {
    return messageService.getTodayAllMessages(
        PageRequest.of(offset, limit)
    );
  }

  @GetMapping("/message/{id}")
  public MessageInfoView getMessageDetailInfo(
      @PathVariable Long id
  ) {
    return messageService.getMessageInfo(id);
  }

  @PatchMapping("/message/{id}")
  public MessageInfoView updateMessage(
      @PathVariable Long id,
      @RequestBody UpdateMessageRequest updateMessageRequest,
      @AuthenticationPrincipal MemberDetails member
  ) {
    return messageService.updateMessage(id, member.getMemberId(), updateMessageRequest);
  }

  @DeleteMapping("/message/{id}")
  public MessageExistInfoView deleteMessage(
      @PathVariable Long id,
      @AuthenticationPrincipal MemberDetails member) {
    return messageService.deleteMessage(id, member.getMemberId());
  }
}
