package com.rocket.birthday.api.auth.response;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaoUserInfoView {
  private Long id;
  private ZonedDateTime connected_at;
  private KakaoAccount kakaoAccount;

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class KakaoAccount {
    private Profile profile;
    private String email;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Profile {
      private String nickname;
      private String profile_image_url;
    }
  }
}
