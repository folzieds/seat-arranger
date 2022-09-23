package com.phos.seatarrangement.core.useradministration.exception;

import com.phos.seatarrangement.core.exception.AbstractPlatformException;

public class UsernameAlreadyExistException extends AbstractPlatformException {
    public UsernameAlreadyExistException(String defaultGlobalCode, String defaultUserMessage) {
        super(defaultGlobalCode, defaultUserMessage);
    }

    public UsernameAlreadyExistException(Throwable cause, String defaultGlobalCode, String defaultUserMessage) {
        super(cause, defaultGlobalCode, defaultUserMessage);
    }
}
