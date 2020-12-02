package com.rover.exceptions;

public class IllegalHeadingException extends RuntimeException {

    private static final String ILLEGAL_HEADING_MESSAGE = "Invalid heading value: ";

    public IllegalHeadingException(String value) {
        super(ILLEGAL_HEADING_MESSAGE + value);
    }

    public IllegalHeadingException(String value, Exception e) {
        super(ILLEGAL_HEADING_MESSAGE + value, e);
    }
}
