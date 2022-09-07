package com.phos.seatarrangement.core.exception;

public abstract class AbstractPlatformException extends RuntimeException {

    private final String defaultGlobalCode;
    private final String defaultUserMessage;

    protected AbstractPlatformException( String defaultGlobalCode, String defaultUserMessage) {
        super(defaultUserMessage);
        this.defaultGlobalCode = defaultGlobalCode;
        this.defaultUserMessage = defaultUserMessage;
    }

    protected AbstractPlatformException( Throwable cause, String defaultGlobalCode, String defaultUserMessage) {
        super(defaultUserMessage, cause);
        this.defaultGlobalCode = defaultGlobalCode;
        this.defaultUserMessage = defaultUserMessage;
    }

    public String getDefaultGlobalCode() {
        return defaultGlobalCode;
    }

    public String getDefaultUserMessage() {
        return defaultUserMessage;
    }
}
