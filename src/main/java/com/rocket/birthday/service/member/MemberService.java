package com.rocket.birthday.service.member;

import com.rocket.birthday.model.member.Member;
import com.rocket.birthday.model.member.MemberProvider;
import com.rocket.birthday.model.member.vo.ProviderType;
import com.rocket.birthday.repository.MemberProviderRepository;
import com.rocket.birthday.repository.MemberRepository;
import com.rocket.birthday.service.auth.AuthService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {


//  Member signUpMember() {
//    Member member = Member.builder()
//        .name(  )
//        .providerId(  )
//        .providerType(  )
//        .email(  )
//        .profileImageUrl(  )
//        .birthday(  )
//        .build();
//    return memberRepository.save(member);
//  }
//
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
    // 토큰 정보 발급

  // }
}
