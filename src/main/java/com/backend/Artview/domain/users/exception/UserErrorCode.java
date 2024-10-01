package com.backend.Artview.domain.users.exception;

import com.backend.Artview.global.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(NOT_FOUND,404,"존재하지 않는 유저입니다."),
    DUPLICATE_KAKAO_ID(CONFLICT, 409,"이미 회원가입 된 카카오 계정 입니다."),
    USER_REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, 404 ,"유저의 refershToken이 없습니다.");

    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String message;

}
