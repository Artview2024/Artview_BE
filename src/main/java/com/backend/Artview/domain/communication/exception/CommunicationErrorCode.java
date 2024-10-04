package com.backend.Artview.domain.communication.exception;

import com.backend.Artview.global.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommunicationErrorCode implements BaseErrorCode {

    //404
    COMMUNICATION_NOT_FOUND(HttpStatus.NOT_FOUND,404,"Communication id에 해당하는 내용을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND,404,"Comment id에 해당하는 댓글을 찾을 수 없습니다."),
    USER_NOT_REGISTER_LIKE(HttpStatus.NOT_FOUND,404,"User가 해당 게시글에 좋아요를 등록하지 않았습니다."),
    COMMUNICATIONS_NO_EXIST(HttpStatus.NOT_FOUND,404,"Communication id에 해당하는 게시글이 존재하지 않습니다."),

    //409
    USER_ALREADY_REGISTER_LIKE(HttpStatus.CONFLICT,409,"User가 이미 좋아요를 등록한 게시글 입니다.");

    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String Message;
}
