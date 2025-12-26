package com.renan.bankingtranscationsapi.exception.handler;

import com.renan.bankingtranscationsapi.exception.*;
import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tools.jackson.databind.exc.InvalidFormatException;

import java.util.Date;

@ControllerAdvice
@RestController
public class ApiExceptionResponseHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(PassingNegativeValueException.class)
  public final ResponseEntity<ExceptionResponse> handlePassingNegativeValueException(
      Exception ex, WebRequest request) {
    ExceptionResponse response =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InsufficientFundsException.class)
  public final ResponseEntity<ExceptionResponse> handleInsufficientFundsException(
      Exception ex, WebRequest request) {
    ExceptionResponse response =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidIDException.class)
  public final ResponseEntity<ExceptionResponse> handleInvalidIDException(
      Exception ex, WebRequest request) {
    ExceptionResponse response =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InitialBalanceBelow100Exception.class)
  public final ResponseEntity<ExceptionResponse> handleInitialBalanceBelow100Exception(
      Exception ex, WebRequest request) {
    ExceptionResponse response =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SameSourceAndDestinationBankAccountIdException.class)
  public final ResponseEntity<ExceptionResponse>
      handleSameSourceAndDestinationBankAccountIdException(Exception ex, WebRequest request) {
    ExceptionResponse response =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidBalanceException.class)
  public final ResponseEntity<ExceptionResponse> handleInvalidBalanceException(
      Exception ex, WebRequest request) {
    ExceptionResponse response =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SinNumberBelow1Exception.class)
  public final ResponseEntity<ExceptionResponse> handleSinNumberBelow1Exception(
      Exception ex, WebRequest request) {
    ExceptionResponse response =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidSinNumberException.class)
  public final ResponseEntity<ExceptionResponse> handleInvalidSinNumberException(
      Exception ex, WebRequest request) {
    ExceptionResponse response =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IncorrectValueTypeException.class)
  public final ResponseEntity<ExceptionResponse> handleTypeMismatchException(
      Exception ex, WebRequest request) {
    ExceptionResponse response =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BankAccountAlreadyExistsException.class)
  public final ResponseEntity<ExceptionResponse> handleankAccountAlreadyExistsException(
      Exception ex, WebRequest request) {
    ExceptionResponse response =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public final ResponseEntity<ExceptionResponse> handleEntityNotFoundException(
      Exception ex, WebRequest request) {
    ExceptionResponse response =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  //  @ExceptionHandler(InvalidFormatException.class)
  //  public final ResponseEntity<ExceptionResponse> handleInvalidFormatException(
  //      Exception ex, WebRequest request) {
  //    ExceptionResponse response =
  //        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
  //    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  //  }

}
