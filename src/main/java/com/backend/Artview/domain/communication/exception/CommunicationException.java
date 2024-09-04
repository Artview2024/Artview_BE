package com.backend.Artview.domain.communication.exception;

import com.backend.Artview.global.exception.ApplicationException;

public class CommunicationException extends ApplicationException {
    private final CommunicationErrorCode communicationErrorCode;

    public CommunicationException(CommunicationErrorCode communicationErrorCode) {
        super(communicationErrorCode);
        this.communicationErrorCode = communicationErrorCode;
    }
}
