package com.rocket.birthday.common.exception.custom.member;

import static com.rocket.birthday.common.exception.enums.BaseErrorCode.MEMBER_NOT_FOUND;

import com.rocket.birthday.common.exception.BusinessException;
import com.rocket.birthday.common.exception.enums.BaseErrorCode;

public class MemberNotFoundException extends BusinessException {
  public MemberNotFoundException(BaseErrorCode errorCode) {
    super(MEMBER_NOT_FOUND);
  }
}
