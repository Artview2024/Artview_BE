package com.backend.Artview.global.exception.S3exception;

import com.backend.Artview.global.exception.ApplicationException;
import com.backend.Artview.global.exception.UtilErrorCode;
import lombok.Getter;

public class S3Exception extends ApplicationException {
    private final UtilErrorCode errorCode;
    public S3Exception(UtilErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
