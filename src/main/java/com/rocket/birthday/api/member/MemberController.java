package com.rocket.birthday.api.member;

import com.rocket.birthday.api.member.dto.response.MemberExistInfoView;
import com.rocket.birthday.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberController {
  private final MemberService memberService;

  @GetMapping("/search")
  public MemberExistInfoView findMember(@RequestParam String nickname) {
    return memberService.findMemberByNickname(nickname);
  }

}
