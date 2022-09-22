package com.phos.seatarrangement.core.useradministration.exception;

import com.phos.seatarrangement.core.exception.AbstractPlatformException;

public class UnAuthenticatedUserException extends AbstractPlatformException {
    public UnAuthenticatedUserException(String defaultGlobalCode, String defaultUserMessage) {
        super(defaultGlobalCode, defaultUserMessage);
    }

    public UnAuthenticatedUserException(Throwable cause, String defaultGlobalCode, String defaultUserMessage) {
        super(cause, defaultGlobalCode, defaultUserMessage);
    }
}
