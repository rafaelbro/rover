package com.rover.exceptions;

public class InvalidFilePathException extends RuntimeException {

  private static final String INVALID_PATH_MESSAGE = "Invalid path provided for file";

  public InvalidFilePathException() {
    super(INVALID_PATH_MESSAGE);
  }

  public InvalidFilePathException(Exception e) {
    super(INVALID_PATH_MESSAGE, e);
  }
}
