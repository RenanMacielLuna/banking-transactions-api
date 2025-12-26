package com.renan.bankingtranscationsapi.controller;

import com.renan.bankingtranscationsapi.dto.BankAccountDTO;
import com.renan.bankingtranscationsapi.exception.IncorrectValueTypeException;
import com.renan.bankingtranscationsapi.model.BankAccount;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import com.renan.bankingtranscationsapi.service.BankAccountService;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class BankAccountController {

  private final BankAccountService bankAccountService;

  public BankAccountController(BankAccountService bankAccountService) {
    this.bankAccountService = bankAccountService;
  }

  @PostMapping(
      value = "/create",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      description =
          "Creating a new Account. If you are using swagger UI, remove the ID from the Request Body.")
  public ResponseEntity<?> createAccount(
      @Valid @RequestBody BankAccount account, BindingResult bindingResult) {
    try {
      if (bindingResult.hasErrors()) {
        return new ResponseEntity<>(
            bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage),
            HttpStatus.BAD_REQUEST);
      }
      Optional<BankAccountDTO> optionalBankAccountDTO = bankAccountService.createAccount(account);
      return new ResponseEntity<>(optionalBankAccountDTO, HttpStatus.CREATED);
    } catch (HttpMessageNotReadableException e) {
      throw new IncorrectValueTypeException("Both the SIN Number, and Balance must be integer.");
    }
  }

  // http://localhost:8080/account/read/{id}
  @GetMapping(value = "/read/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(description = "Reading the account's values")
  public ResponseEntity<?> getAccountById(@PathVariable String id) {
    Optional<BankAccountDTO> optionalBankAccountDTO = bankAccountService.getAccountById(id);
    if (optionalBankAccountDTO.isPresent()) {
      return new ResponseEntity<>(optionalBankAccountDTO, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
