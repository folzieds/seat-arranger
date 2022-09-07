package com.phos.seatarrangement.core.exception;

public class PlatformDataIntegrityException extends AbstractPlatformException{

    public PlatformDataIntegrityException(String defaultGlobalCode, String defaultUserMessage) {
        super(defaultGlobalCode, defaultUserMessage);
    }

    public PlatformDataIntegrityException(Throwable cause, String defaultGlobalCode, String defaultUserMessage) {
        super(cause, defaultGlobalCode, defaultUserMessage);
    }
}
