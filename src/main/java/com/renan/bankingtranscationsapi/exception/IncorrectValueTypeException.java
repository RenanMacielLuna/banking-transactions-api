package com.renan.bankingtranscationsapi.exception;

public class IncorrectValueTypeException extends RuntimeException {
  public IncorrectValueTypeException(String message) {
    super(message);
  }
}
