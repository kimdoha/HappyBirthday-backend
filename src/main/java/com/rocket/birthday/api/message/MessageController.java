package com.rocket.birthday.api.message;

import com.rocket.birthday.api.message.dto.request.PostMessageRequest;
import com.rocket.birthday.api.message.dto.request.UpdateMessageRequest;
import com.rocket.birthday.api.message.dto.response.MessageInfoView;
import com.rocket.birthday.service.member.dtos.MemberDetails;
import com.rocket.birthday.service.message.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
@RestController
public class MessageController {
  private final MessageService messageService;
  @PostMapping("")
  public MessageInfoView postMessage(
      @RequestBody @Valid PostMessageRequest postMessageRequest,
      @AuthenticationPrincipal MemberDetails member
  ) {
    return messageService.createMessage(member.getMemberId(), postMessageRequest );
  }

  @GetMapping("/{id}")
  public MessageInfoView getMessageDetailInfo(@PathVariable Long id) {
    return messageService.getMessageInfo(id);
  }

  @PatchMapping("/{id}")
  public MessageInfoView updateMessage(
      @PathVariable Long id,
      @RequestBody UpdateMessageRequest updateMessageRequest,
      @AuthenticationPrincipal MemberDetails member
  ) {
    return messageService.updateMessage(id, member.getMemberId(), updateMessageRequest);
  }

  @DeleteMapping("/{id}")
  public String deleteMessage(@PathVariable Long id) {
    return "delete-message";
  }
}
