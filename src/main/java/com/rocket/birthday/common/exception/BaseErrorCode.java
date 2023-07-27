package com.rocket.birthday.common.exception;


import static com.rocket.birthday.common.constant.BirthdayConstants.BAD_REQUEST;
import static com.rocket.birthday.common.constant.BirthdayConstants.INTERNAL_SERVER;
import static com.rocket.birthday.common.constant.BirthdayConstants.NOT_FOUND;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseErrorCode {
  INNER_SERVER_ERROR(INTERNAL_SERVER, "예상치 못한 서버 문제가 발생하였습니다." ),
  INVALID_PROVIDER_ID( BAD_REQUEST, "소셜 로그인 인증이 적절하지 않습니다." ),
  MEMBER_NOT_FOUND( NOT_FOUND, "해당 회원을 찾을 수 없습니다." );

  private Integer code;
  private String reason;
}

