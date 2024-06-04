package com.backend.Artview.global.code;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    HttpStatus getHttpStatus();

    int getStatusCode();

    String getMessage();
}
