package com.rocket.birthday.common.exception.enums;

import static com.rocket.birthday.common.constant.BirthdayConstants.BAD_REQUEST;
import static com.rocket.birthday.common.constant.BirthdayConstants.CONFLICT;
import static com.rocket.birthday.common.constant.BirthdayConstants.FORBIDDEN;
import static com.rocket.birthday.common.constant.BirthdayConstants.INTERNAL_SERVER;
import static com.rocket.birthday.common.constant.BirthdayConstants.NOT_FOUND;
import static com.rocket.birthday.common.constant.BirthdayConstants.TOO_MANY_REQUEST;
import static com.rocket.birthday.common.constant.BirthdayConstants.UNAUTHORIZED;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseErrorCode {
  // [KAPI - KAKAO API]
  INVALID_REQUEST(BAD_REQUEST, "필수 파라미터와 관련한 bad request 오류입니다."),
  UNAUTHORIZED_ERROR(UNAUTHORIZED, "해당 리소스에 유효한 인증 자격 증명이 없어 요청에 실패하였습니다."),
  FORBIDDEN_ERROR(FORBIDDEN, "서버에 요청이 전달되었지만, 권한 때문에 거절된 상태입니다."),
  TOO_MANY_REQUEST_ERROR(TOO_MANY_REQUEST, "정해진 사용량이나 초당 요청 한도를 초과한 경우"),

  INNER_SERVER_ERROR(INTERNAL_SERVER, "예상치 못한 서버 문제가 발생하였습니다." ),

  INVALID_PROVIDER_ID(BAD_REQUEST, "소셜 로그인 인증이 적절하지 않습니다." ),

  // [MEMBER]
  MEMBER_NOT_FOUND(NOT_FOUND, "해당 회원을 찾을 수 없습니다."),
  MESSAGE_RECEIVER_NOT_FOUND(NOT_FOUND, "메세지 받는 이를 찾을 수 없습니다."),

  // [MESSAGE]
  NOT_AVAILABLE_MESSAGE_CREATE(CONFLICT, "생일 메세지는 본인에게 작성할 수 없습니다."),
  NOT_AVAILABLE_MESSAGE_CREATE_BEFORE_NOW(CONFLICT, "생일 메세지 오픈 날짜는 현재 시각 이후여야 합니다."),
  MESSAGE_NOT_FOUND(NOT_FOUND, "해당 메세지를 찾을 수 없습니다."),
  NOT_AVAILABLE_MESSAGE_UPDATE(CONFLICT, "생일 메세지는 작성자만 수정할 수 있습니다."),
  NOT_AVAILABLE_MESSAGE_UPDATE_AFTER_DATE(CONFLICT, "메세지 오픈 날짜 이전에만 수정이 가능합니다."),
  NOT_AVAILABLE_MESSAGE_DELETE(CONFLICT, "생일 메세지는 작성자만 삭제할 수 있습니다."),
  INVALID_MESSAGE_CREATE_TYPE(BAD_REQUEST, "메세지 생성 타입이 올바르지 않습니다.");

  private Integer code;
  private String reason;
}

