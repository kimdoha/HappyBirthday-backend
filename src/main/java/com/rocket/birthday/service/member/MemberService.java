package com.rocket.birthday.service.member;

import com.rocket.birthday.api.auth.response.KakaoUserInfoView;
import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.repository.member.MemberRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  @Transactional(readOnly = true)
  public Member findOne(Long id) {
    return memberRepository.findById(id).get();
  }

  @Transactional
  public Member create(KakaoUserInfoView kakaoUserInfo) {

    Member member = Member.builder()
        .email(kakaoUserInfo.getKakao_account().getEmail())
        .nickname(kakaoUserInfo.getKakao_account().getProfile().getNickname())
        .profileImageUrl(kakaoUserInfo.getKakao_account().getProfile().getProfile_image_url())
        .build();

    return memberRepository.save(member);
  }
}
