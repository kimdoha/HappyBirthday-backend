package com.rocket.birthday.common.exception;

import com.rocket.birthday.common.exception.custom.auth.KAuthException;
import com.rocket.birthday.common.exception.dto.ErrorResponse;
import com.rocket.birthday.common.exception.enums.BaseErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> businessExceptionHandler(
      BusinessException e,
      HttpServletRequest request
  ) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .code(e.getErrorCode().getCode())
        .reason(e.getErrorCode().getReason())
        .build();
    return ResponseEntity.status(HttpStatus.valueOf(e.getErrorCode().getCode()))
        .body(errorResponse);
  }

  @ExceptionHandler(KAuthException.class)
  public ResponseEntity<ErrorResponse> kAuthExceptionHandler(
      KAuthException e,
      HttpServletRequest request
  ) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .code(e.getErrorCode().getCode())
        .reason(e.getErrorCode().getReason())
        .build();
    return ResponseEntity.status(HttpStatus.valueOf(e.getErrorCode().getCode()))
        .body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> exceptionHandler(
      Exception e,
      HttpServletRequest request
  ) {
    BaseErrorCode errorCode = BaseErrorCode.INNER_SERVER_ERROR;
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .code(errorCode.getCode())
            .reason(errorCode.getReason())
            .build();

    return ResponseEntity.internalServerError()
        .body(errorResponse);
  }
}
