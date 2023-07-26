package com.rocket.birthday.service.member;

import static com.rocket.birthday.common.exception.ErrorCode.MEMBER_NOT_FOUND;

import com.rocket.birthday.api.auth.response.KakaoUserInfoView;
import com.rocket.birthday.common.exception.ErrorCode;
import com.rocket.birthday.common.exception.custom.member.MemberNotFoundException;
import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.member.MemberProvider;
import com.rocket.birthday.model.member.vo.ProviderType;
import com.rocket.birthday.repository.MemberRepository;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public Member findMemberByProvider(String providerId, ProviderType type) {
    return memberRepository.getMemberByProviderId(providerId, type);
  }

  public Member create(KakaoUserInfoView kakaoUserInfo, MemberProvider memberProvider) {

    Member newMember = Member.builder()
        .email(kakaoUserInfo.getKakao_account().getEmail())
        .name(kakaoUserInfo.getKakao_account().getProfile().getNickname())
        .profileImageUrl(kakaoUserInfo.getKakao_account().getProfile().getProfile_image_url())
        .birthday(LocalDate.now())
        .memberProvider(memberProvider)
        .build();

    return memberRepository.save(newMember);
  }
}
