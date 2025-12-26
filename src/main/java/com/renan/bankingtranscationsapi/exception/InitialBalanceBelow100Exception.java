package com.renan.bankingtranscationsapi.exception;

public class InitialBalanceBelow100Exception extends RuntimeException {
  public InitialBalanceBelow100Exception(String message) {
    super(message);
  }
}
