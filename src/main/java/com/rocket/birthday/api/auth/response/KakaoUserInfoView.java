package com.rocket.birthday.api.auth.response;

import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoView {
  private Long id;
  private ZonedDateTime connected_at;
  private KakaoAccount kakao_account;

  @Getter
  @NoArgsConstructor
  public static class KakaoAccount {
    private Boolean profile_needs_agreement;
    private Profile profile;
    private Boolean has_email;
    private Boolean email_needs_agreement;
    private Boolean is_email_valid;
    private Boolean is_email_verified;
    private String email;

    @Getter
    @NoArgsConstructor
    public static class Profile {
      private String nickname;
      private String thumbnail_image_url;
      private String profile_image_url;
      private Boolean is_default_image;
    }
  }
}
