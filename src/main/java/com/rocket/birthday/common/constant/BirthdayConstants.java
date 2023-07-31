package com.rocket.birthday.common.constant;

import org.springframework.http.HttpStatus;

public class BirthdayConstants {
  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String BEARER = "Bearer ";
  public static final String PROPERTY_KEYS = "[\"kakao_account.profile\", \"kakao_account.name\", \"kakao_account.email\"]";

  public static final int BAD_REQUEST = HttpStatus.BAD_REQUEST.value();
  public static final int UNAUTHORIZED = HttpStatus.UNAUTHORIZED.value();
  public static final int FORBIDDEN = HttpStatus.FORBIDDEN.value();
  public static final int NOT_FOUND = HttpStatus.NOT_FOUND.value();
  public static final int TOO_MANY_REQUEST = HttpStatus.TOO_MANY_REQUESTS.value();
  public static final int INTERNAL_SERVER = HttpStatus.INTERNAL_SERVER_ERROR.value();
}
