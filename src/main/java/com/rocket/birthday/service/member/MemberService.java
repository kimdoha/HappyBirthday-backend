package com.rocket.birthday.service.member;

import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.member.MemberProvider;
import com.rocket.birthday.repository.MemberRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  public Member createMember(
      String email,
      String name,
      String profileImageUrl,
      LocalDate birthday,
      MemberProvider memberProvider) {

    Member newMember = Member.builder()
        .email(email)
        .name(name)
        .profileImageUrl(profileImageUrl)
        .birthday(birthday)
        .memberProvider(memberProvider)
        .build();

    return memberRepository.save(newMember);
  }

//  void signIneMember(String accessToken, body) {
//    KakaoUserInfoView kakaoInfo = authService.getKakaoUserInfo(accessToken);
//
//    String providerId = String.valueOf(kakaoInfo.getId());
//    Optional<Member> member = memberRepository.findByProviderId(providerId);
//    if(member.isPresent()) {
//
//    } else {
//
//    }

}
