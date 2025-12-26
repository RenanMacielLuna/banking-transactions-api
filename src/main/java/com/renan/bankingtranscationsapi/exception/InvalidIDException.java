package com.renan.bankingtranscationsapi.exception;

public class InvalidIDException extends RuntimeException {
  public InvalidIDException(String message) {
    super(message);
  }
}
