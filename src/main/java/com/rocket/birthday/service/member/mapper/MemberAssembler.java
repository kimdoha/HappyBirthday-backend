package com.rocket.birthday.service.member.mapper;

import com.rocket.birthday.api.auth.response.KakaoUserInfoView;
import com.rocket.birthday.model.member.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberAssembler {

  public MemberEntity toMemberEntity(KakaoUserInfoView kakaoUserInfo) {
    return MemberEntity.builder()
        .email(kakaoUserInfo.getKakao_account().getEmail())
        .nickname(kakaoUserInfo.getKakao_account().getProfile().getNickname())
        .profileImageUrl(kakaoUserInfo.getKakao_account().getProfile().getProfile_image_url())
        .build();
  }
}
