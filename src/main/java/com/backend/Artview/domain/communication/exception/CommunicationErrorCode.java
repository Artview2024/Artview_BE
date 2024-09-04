package com.backend.Artview.domain.communication.exception;

import com.backend.Artview.global.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommunicationErrorCode implements BaseErrorCode {

    COMMUNICATION_NOT_FOUND(HttpStatus.NOT_FOUND,404,"Communication id에 해당하는 내용을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND,404,"Comment id에 해당하는 댓글을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String Message;
}
