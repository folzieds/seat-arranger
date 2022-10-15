package com.phos.seatarrangement.document.exception;

import com.phos.seatarrangement.core.exception.AbstractPlatformException;

public class DocumentNotFoundException extends AbstractPlatformException {

    public DocumentNotFoundException(String defaultGlobalCode, String defaultUserMessage) {
        super(defaultGlobalCode, defaultUserMessage);
    }

    public DocumentNotFoundException(Throwable cause, String defaultGlobalCode, String defaultUserMessage) {
        super(cause, defaultGlobalCode, defaultUserMessage);
    }
}
