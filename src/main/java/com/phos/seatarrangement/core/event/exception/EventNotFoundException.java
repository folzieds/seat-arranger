package com.phos.seatarrangement.core.event.exception;

import com.phos.seatarrangement.core.exception.AbstractPlatformException;

public class EventNotFoundException extends AbstractPlatformException {

    public EventNotFoundException(final String globalisationMessageCode, final String defaultUserMessage) {
        super(globalisationMessageCode, defaultUserMessage);
    }

    public EventNotFoundException(String defaultGlobalCode, String defaultUserMessage, Throwable cause) {
        super(cause, defaultGlobalCode, defaultUserMessage);
    }
}
