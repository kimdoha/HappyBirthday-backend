package com.rocket.birthday.common.exception;

import com.rocket.birthday.common.exception.custom.BusinessException;
import com.rocket.birthday.common.exception.custom.auth.KAuthException;
import com.rocket.birthday.common.exception.dto.BaseErrorResponse;
import com.rocket.birthday.common.exception.dto.KAuthErrorResponse;
import com.rocket.birthday.common.exception.enums.BaseErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<BaseErrorResponse> businessExceptionHandler(
      BusinessException e,
      HttpServletRequest request
  ) {
    BaseErrorResponse baseErrorResponse = BaseErrorResponse.builder()
        .code(e.getErrorCode().getCode())
        .reason(e.getErrorCode().getReason())
        .build();
    return ResponseEntity.status(HttpStatus.valueOf(e.getErrorCode().getCode()))
        .body( baseErrorResponse );
  }

  @ExceptionHandler(KAuthException.class)
  public ResponseEntity<KAuthErrorResponse> kAuthExceptionHandler(
      KAuthException e,
      HttpServletRequest request
  ) {
    KAuthErrorResponse errorResponse = KAuthErrorResponse.builder()
        .code(e.getErrorCode().getCode())
        .errorCode(e.getErrorCode().getErrorCode())
        .error(e.getErrorCode().getError())
        .reason(e.getErrorCode().getReason())
        .build();

    return ResponseEntity.status(HttpStatus.valueOf(e.getErrorCode().getCode()))
        .body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseErrorResponse> exceptionHandler(
      Exception e,
      HttpServletRequest request
  ) {
    BaseErrorCode errorCode = BaseErrorCode.INNER_SERVER_ERROR;
    BaseErrorResponse baseErrorResponse =
        BaseErrorResponse.builder()
            .code(errorCode.getCode())
            .reason(errorCode.getReason())
            .build();

    return ResponseEntity.internalServerError()
        .body( baseErrorResponse );
  }
}
