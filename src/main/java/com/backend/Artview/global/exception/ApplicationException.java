package com.backend.Artview.global.exception;

import com.backend.Artview.global.code.BaseErrorCode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final BaseErrorCode errorCode;

    public ApplicationException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
