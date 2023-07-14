package com.rocket.birthday.api.auth.response;

import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoView {
  private Long id;
  private ZonedDateTime connected_at;
  private KakaoAccount kakaoAccount;

  @Getter
  @NoArgsConstructor
  public static class KakaoAccount {
    private Profile profile;
    private String email;

    @Getter
    @NoArgsConstructor
    public static class Profile {
      private String nickname;
      private String profile_image_url;
    }
  }
}
