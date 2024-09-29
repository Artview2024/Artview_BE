package com.backend.Artview.global.jwt.jwtException;

import com.backend.Artview.global.exception.ApplicationException;

public class JwtException extends ApplicationException {

    public JwtException(JwtErrorCode errorCode) {
        super(errorCode);
    }
}