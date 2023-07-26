package com.rocket.birthday.common.exception.custom.member;

import static com.rocket.birthday.common.exception.ErrorCode.MEMBER_NOT_FOUND;

import com.rocket.birthday.common.exception.BusinessException;
import com.rocket.birthday.common.exception.ErrorCode;

public class MemberNotFoundException extends BusinessException {
  public MemberNotFoundException(ErrorCode errorCode) {
    super(MEMBER_NOT_FOUND);
  }
}
