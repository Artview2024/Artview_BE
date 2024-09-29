package com.backend.Artview.global.jwt.jwtException;

import com.backend.Artview.global.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum JwtErrorCode implements BaseErrorCode {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 401, "접근할 수 있는 권한이 없습니다. access token을 확인하세요."),
    INVALID_JWT_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, 401, "유효하지 않은 ACCESS TOKEN 입니다"),
    EXPIRED_JWT_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, 401, "ACCESS TOKEN이 만료되었습니다. 재발급 받아주세요."),
    INVALID_JWT_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, 401, "유효하지 않은 REFRESH TOKEN 입니다."),
    EXPIRED_JWT_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, 401, "REFRESH TOKEN이 만료되었습니다. 다시 로그인해주세요"),
    TOKEN_TYPE_NOT_MATCH(HttpStatus.UNAUTHORIZED, 401, "토큰 타입이 맞지 않습니다.");


    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String message;
}
