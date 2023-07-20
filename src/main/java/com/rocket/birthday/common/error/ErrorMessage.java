package com.rocket.birthday.common.error;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
  INNER_SERVER_ERROR(INTERNAL_SERVER_ERROR, "예상치 못한 서버 문제가 발생하였습니다." ),
  MEMBER_NOT_FOUND(NOT_FOUND, "해당 회원을 찾을 수 없습니다."),
  INVALID_PROVIDER_ID(BAD_REQUEST, "소셜 로그인 인증이 적절하지 않습니다.");

  private final int code;
  private final String message;

  ErrorMessage(HttpStatus status, String message) {
    this.code = status.value();
    this.message = message;
  }
}

