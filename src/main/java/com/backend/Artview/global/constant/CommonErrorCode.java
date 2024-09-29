package com.backend.Artview.global.constant;

import com.backend.Artview.global.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements BaseErrorCode {
    // 전역 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버 내부 오류입니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "입력 값이 잘못된 요청 입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 401, "인증이 필요 합니다.");

    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String message;
}