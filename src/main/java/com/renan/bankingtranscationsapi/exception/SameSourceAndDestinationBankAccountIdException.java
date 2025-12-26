package com.renan.bankingtranscationsapi.exception;

public class SameSourceAndDestinationBankAccountIdException extends RuntimeException {
  public SameSourceAndDestinationBankAccountIdException(String message) {
    super(message);
  }
}
