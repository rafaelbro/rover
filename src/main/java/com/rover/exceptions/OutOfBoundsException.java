package com.rover.exceptions;

public class OutOfBoundsException extends RuntimeException {

    private static final String OUT_OF_BOUNDS_MESSAGE = "Cannot perform movement, this rover is going rougue(off the grid)!";

    public OutOfBoundsException() {
        super(OUT_OF_BOUNDS_MESSAGE);
    }

    public OutOfBoundsException(Exception e) {
        super(OUT_OF_BOUNDS_MESSAGE, e);
    }
}
