package com.rocket.birthday.common.exception.custom.member;

import com.rocket.birthday.common.exception.BusinessException;
import com.rocket.birthday.common.exception.enums.BaseErrorCode;

public class MemberNotFoundException extends BusinessException {
  public static final MemberNotFoundException EXCEPTION = new MemberNotFoundException(BaseErrorCode.MEMBER_NOT_FOUND);
  public MemberNotFoundException(BaseErrorCode errorCode) {
    super(errorCode);
  }
}
