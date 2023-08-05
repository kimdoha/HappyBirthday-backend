package com.rocket.birthday.service.member.mapper;

import com.rocket.birthday.api.auth.response.KakaoUserInfoView;
import com.rocket.birthday.api.member.response.MemberExistInfoView;
import com.rocket.birthday.model.member.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
  public MemberExistInfoView toMemberExistInfoView(Long id) {
    return MemberExistInfoView.builder()
        .id(id)
        .exist(true)
        .build();
  }

  public Member toEntity(KakaoUserInfoView kakaoUserInfo) {
    return Member.builder()
        .email(kakaoUserInfo.getKakao_account().getEmail())
        .nickname(kakaoUserInfo.getKakao_account().getProfile().getNickname())
        .profileImageUrl(kakaoUserInfo.getKakao_account().getProfile().getProfile_image_url())
        .build();
  }
}
