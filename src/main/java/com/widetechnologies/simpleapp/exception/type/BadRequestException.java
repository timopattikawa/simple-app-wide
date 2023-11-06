package com.widetechnologies.simpleapp.exception.type;

public class BadRequestException extends RuntimeException{

    private final String message;
    private final String errReason;

    public BadRequestException(String message, String errReason) {
        this.message = message;
        this.errReason = errReason;
    }

    public BadRequestException(String message, Throwable cause, boolean enableSuppression,
                               boolean writableStackTrace,
                               String message1,
                               String errReason) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.errReason = errReason;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getErrReason() {
        return errReason;
    }
}
