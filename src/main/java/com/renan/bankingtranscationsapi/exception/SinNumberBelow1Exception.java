package com.renan.bankingtranscationsapi.exception;

public class SinNumberBelow1Exception extends RuntimeException {
  public SinNumberBelow1Exception(String message) {
    super(message);
  }
}
