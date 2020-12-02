package com.rover.exceptions;

public class InvalidFileFormatException extends RuntimeException {

  public InvalidFileFormatException(String message) {
    super(message);
  }

  public InvalidFileFormatException(String message, Exception e) {
    super(message, e);
  }
}
