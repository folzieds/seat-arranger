package com.phos.seatarrangement.core.useradministration.exception;

import com.phos.seatarrangement.core.exception.AbstractPlatformException;

public class UsernameAlreadyExistException extends AbstractPlatformException {
    protected UsernameAlreadyExistException(String defaultGlobalCode, String defaultUserMessage) {
        super(defaultGlobalCode, defaultUserMessage);
    }

    protected UsernameAlreadyExistException(Throwable cause, String defaultGlobalCode, String defaultUserMessage) {
        super(cause, defaultGlobalCode, defaultUserMessage);
    }
}
