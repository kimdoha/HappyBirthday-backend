package com.rocket.birthday.api.member;

import com.rocket.birthday.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/member")
@RequiredArgsConstructor
@RestController
public class MemberController {
  private final MemberService memberService;


}
