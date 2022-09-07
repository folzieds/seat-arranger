package com.phos.seatarrangement.core.guest.exception;

import com.phos.seatarrangement.core.exception.AbstractPlatformException;

public class GuestNotFoundException extends AbstractPlatformException {

    public GuestNotFoundException(String defaultGlobalCode, String defaultUserMessage) {
        super(defaultGlobalCode, defaultUserMessage);
    }

    public GuestNotFoundException( String defaultGlobalCode, String defaultUserMessage, Throwable cause) {
        super(cause, defaultGlobalCode, defaultUserMessage);
    }
}
