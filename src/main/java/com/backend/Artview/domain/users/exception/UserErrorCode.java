package com.backend.Artview.domain.users.exception;

import com.backend.Artview.global.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    //404
    USER_NOT_FOUND(NOT_FOUND,404,"존재하지 않는 유저입니다."),
    DUPLICATE_KAKAO_ID(CONFLICT, 409,"이미 회원가입 된 카카오 계정 입니다."),
    USER_REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, 404 ,"유저의 refershToken이 없습니다."),
    FOLLOW_USER_NOT_FOUND(NOT_FOUND, 404 ,"팔로우 하려는 유저의 id가 존재하지 않습니다."),

    //409
    USER_ALREADY_FOLLOW(CONFLICT, 409 ,"유저가 이미 팔로우 하고 있는 사용자입니다."),
    USER_ALREADY_UNFOLLOW(CONFLICT, 409 ,"유저가 팔로우 하고 있지 않는 사용자입니다.");

    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String message;

}
