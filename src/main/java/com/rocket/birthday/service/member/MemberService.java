package com.rocket.birthday.service.member;

import com.rocket.birthday.api.auth.dto.response.KakaoUserInfoView;
import com.rocket.birthday.common.exception.custom.member.MemberNotFoundException;
import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.repository.member.MemberRepository;
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
    return memberRepository.findById(id)
        .orElseThrow(() -> MemberNotFoundException.EXCEPTION);
  }

  @Transactional(readOnly = true)
  public Member findOneByNickname(String nickname) {
    return memberRepository.findByNickname(nickname)
        .orElseThrow(() -> MemberNotFoundException.EXCEPTION);
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
