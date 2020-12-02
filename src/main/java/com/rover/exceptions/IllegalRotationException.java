package com.rover.exceptions;

public class IllegalRotationException extends RuntimeException {

    private static final String ILLEGAL_ROTATION_MESSAGE = "Invalid roation value: ";

    public IllegalRotationException(String value) {
        super(ILLEGAL_ROTATION_MESSAGE + value);
    }

    public IllegalRotationException(String value, Exception e) {
        super(ILLEGAL_ROTATION_MESSAGE + value, e);
    }
}
