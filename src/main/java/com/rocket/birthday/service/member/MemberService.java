package com.rocket.birthday.service.member;

import static com.rocket.birthday.common.exception.enums.BaseErrorCode.MEMBER_NOT_FOUND;

import com.rocket.birthday.api.auth.response.KakaoUserInfoView;
import com.rocket.birthday.api.member.response.MemberExistInfoView;
import com.rocket.birthday.service.member.mapper.MemberMapper;
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
  private final MemberMapper memberMapper;

  @Transactional(readOnly = true)
  public Member findOne(Long id) {
    return memberRepository.findById(id)
        .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));
  }

  @Transactional(readOnly = true)
  public MemberExistInfoView findMemberByNickname(Long memberId, String nickname) {

    Member member = memberRepository.findByNickname(nickname)
        .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND) );

    return MemberMapper.from(member.getId());
  }

  @Transactional
  public Member create(KakaoUserInfoView kakaoUserInfo) {

    Member member = memberMapper.toEntity(kakaoUserInfo);
    return memberRepository.save(member);
  }
}
