package com.backend.Artview.global.exception;

import com.backend.Artview.global.constant.CommonErrorCode;

public class ForbiddenException extends ApplicationException{
    public ForbiddenException(CommonErrorCode errorCode) {
        super(errorCode);
    }
}
