package com.renan.bankingtranscationsapi.exception;

public class BankAccountAlreadyExistsException extends RuntimeException {
  public BankAccountAlreadyExistsException(String message) {
    super(message);
  }
}
