package com.rocket.birthday.common.exception.enums;


import static com.rocket.birthday.common.constant.BirthdayConstants.BAD_REQUEST;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KAuthErrorCode {
  KOE101(BAD_REQUEST, "KAKAO_KOE101", "invalid_client", "잘못된 앱 키 또는 앱 키 타입을 사용한 경우"),
  KOE009(BAD_REQUEST, "KAKAO_KOE009", "misconfigured", "등록되지 않은 플랫폼에서 액세스 토큰을 요청한 경우"),
  KOE010(BAD_REQUEST, "KAKAO_KOE010", "invalid_client", "클라이언트 시크릿(Client secret) 기능을 사용하는 앱에서 토큰 요청 시 client_secret 값을 누락했거나 잘못된 값을 전달한 경우"),
  KOE303(BAD_REQUEST, "KAKAO_KOE303", "invalid_grant", "인가 코드 요청과 액세스 토큰 요청 시 사용한 redirect_uri가 서로 다른 경우"),
  KOE319(BAD_REQUEST, "KAKAO_KOE319", "invalid_grant", "토큰 갱신 요청 시 리프레시 토큰을 전달하지 않은 경우"),
  KOE320(BAD_REQUEST, "KAKAO_KOE320", "invalid_grant", "동일한 인가 코드를 두 번 이상 사용하거나, 이미 만료된 인가 코드를 사용한 경우, 혹은 인가 코드를 찾을 수 없는 경우"),
  KOE322(BAD_REQUEST, "KAKAO_KOE322", "invalid_grant", "refresh_token을 찾을 수 없거나 이미 만료된 리프레시 토큰을 사용한 경우"),
  INVALID_REQUEST(BAD_REQUEST, "KAKAO_INVALID_REQUEST", "invalid_request", "잘못된 요청인 경우");

  private Integer code;
  private String errorCode;
  private String error;
  private String reason;

}

