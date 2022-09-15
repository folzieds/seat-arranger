package com.phos.seatarrangement.core.document.exception;

import com.phos.seatarrangement.core.exception.AbstractPlatformException;

public class DocumentNotFoundException extends AbstractPlatformException {

    protected DocumentNotFoundException(String defaultGlobalCode, String defaultUserMessage) {
        super(defaultGlobalCode, defaultUserMessage);
    }

    protected DocumentNotFoundException(Throwable cause, String defaultGlobalCode, String defaultUserMessage) {
        super(cause, defaultGlobalCode, defaultUserMessage);
    }
}
