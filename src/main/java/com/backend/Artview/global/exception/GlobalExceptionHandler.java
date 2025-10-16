package com.backend.Artview.global.exception;

import com.backend.Artview.global.code.BaseErrorCode;
import com.backend.Artview.global.constant.CommonErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity<Object> handleApplicationException(ApplicationException exception) {
        log.error("{}: {}", exception.getClass().getSimpleName(), exception.getMessage(), exception);
        return handleExceptionInternal(exception.getErrorCode());
    }

    //잘못된 파라미터
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException exception) {
        log.error("잘못된 인수 예외 처리 : ", exception);
        BaseErrorCode errorCode = CommonErrorCode.BAD_REQUEST;
        return handleExceptionInternal(errorCode);
    }

    @ExceptionHandler(TypeMismatchException.class)
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException exception){
        log.error("타입 불일치 예외 : "+exception);
        BaseErrorCode typeMismatch = CommonErrorCode.TYPE_MISMATCH;
        return handleExceptionInternal(typeMismatch);
    }

    private ResponseEntity<Object> handleExceptionInternal(BaseErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ErrorResponse.of(errorCode));
    }
}
