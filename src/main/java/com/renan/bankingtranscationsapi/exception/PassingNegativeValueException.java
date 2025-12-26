package com.renan.bankingtranscationsapi.exception;

public class PassingNegativeValueException extends RuntimeException {
  public PassingNegativeValueException(String message) {
    super(message);
  }
}
