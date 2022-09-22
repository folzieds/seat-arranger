package com.phos.seatarrangement.core.useradministration.exception;

import com.phos.seatarrangement.core.exception.AbstractPlatformException;

public class UserNotFoundException extends AbstractPlatformException {
    protected UserNotFoundException(String defaultGlobalCode, String defaultUserMessage) {
        super(defaultGlobalCode, defaultUserMessage);
    }

    protected UserNotFoundException(Throwable cause, String defaultGlobalCode, String defaultUserMessage) {
        super(cause, defaultGlobalCode, defaultUserMessage);
    }
}
