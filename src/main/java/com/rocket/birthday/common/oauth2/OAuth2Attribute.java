package com.rocket.birthday.common.oauth2;

import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuth2Attribute {

  private Map<String, Object> attributes;
  private String attributeKey;
  private String email;
  private String name;
  private String picture;

  static OAuth2Attribute of(String provider, String attributeKey, Map<String, Object> attributes) {
    switch (provider) {
      case "google":
        return ofGoogle(attributeKey, attributes);
      case "kakao":
        return ofKakao("email",attributes);
      default:
        throw new RuntimeException();
    }
  }

  private static OAuth2Attribute ofGoogle(String attributeKey, Map<String, Object> attributes) {
    return OAuth2Attribute.builder()
        .name((String) attributes.get("name"))
        .email((String) attributes.get("email"))
        .picture((String) attributes.get("picture"))
        .attributes(attributes)
        .attributeKey(attributeKey)
        .build();
  }

  private static OAuth2Attribute ofKakao(String attributeKey, Map<String, Object> attributes) {
    Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
    Map<String, Object> kakaoProfile = (Map<String, Object>) attributes.get("profile");

    return OAuth2Attribute.builder()
        .name((String) kakaoProfile.get("nickname"))
        .email((String) kakaoAccount.get("email"))
        .picture((String) kakaoProfile.get("profile_image_url"))
        .attributes(kakaoAccount)
        .attributeKey(attributeKey)
        .build();
  }
  public Map<String, Object> converToMap() {
    Map<String, Object> map = new HashMap<>();
    map.put("id", attributeKey);
    map.put("key", attributeKey);
    map.put("name", name);
    map.put("email", email);
    map.put("picture", picture);

    return map;
  }
}

