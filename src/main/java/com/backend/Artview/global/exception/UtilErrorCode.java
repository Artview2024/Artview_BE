package com.backend.Artview.global.exception;

import com.backend.Artview.global.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UtilErrorCode implements BaseErrorCode {

    //S3 관련
    S3_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 500, "S3 파일 업로드에 실패했습니다."),
    S3_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 500, "S3 파일 삭제에 실패했습니다.");

    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String message;

}
