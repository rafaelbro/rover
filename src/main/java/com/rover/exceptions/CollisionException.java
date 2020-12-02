package com.rover.exceptions;

public class CollisionException extends RuntimeException {

  public CollisionException(String message) {
    super(message);
  }

  public CollisionException(String message, Exception e) {
    super(message, e);
  }
}
