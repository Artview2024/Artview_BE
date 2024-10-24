package com.backend.Artview.global.exception.s3Exception;

import com.backend.Artview.global.exception.ApplicationException;

public class S3Exception extends ApplicationException {
    private final S3UtilErrorCode errorCode;
    public S3Exception(S3UtilErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
