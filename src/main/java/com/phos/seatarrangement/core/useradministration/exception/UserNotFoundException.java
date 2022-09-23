package com.phos.seatarrangement.core.useradministration.exception;

import com.phos.seatarrangement.core.exception.AbstractPlatformException;

public class UserNotFoundException extends AbstractPlatformException {
    public UserNotFoundException(String defaultGlobalCode, String defaultUserMessage) {
        super(defaultGlobalCode, defaultUserMessage);
    }

    public UserNotFoundException(Throwable cause, String defaultGlobalCode, String defaultUserMessage) {
        super(cause, defaultGlobalCode, defaultUserMessage);
    }
}
