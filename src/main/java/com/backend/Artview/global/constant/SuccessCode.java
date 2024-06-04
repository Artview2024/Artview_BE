package com.backend.Artview.global.constant;

import com.backend.Artview.global.code.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode implements BaseCode {

    OK(HttpStatus.OK, 200, "성공");


    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String message;

}
