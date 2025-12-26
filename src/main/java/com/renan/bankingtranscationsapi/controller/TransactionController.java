package com.renan.bankingtranscationsapi.controller;

import com.renan.bankingtranscationsapi.dto.BankAccountDTO;
import com.renan.bankingtranscationsapi.dto.TransactionDTO;
import com.renan.bankingtranscationsapi.model.BankAccount;
import com.renan.bankingtranscationsapi.model.Transaction;
import com.renan.bankingtranscationsapi.service.BankAccountService;
import com.renan.bankingtranscationsapi.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  // http://localhost:8080/transaction/read/{id}
  @GetMapping(value = "/read/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(description = "Reading all transactions with the given ID.")
  public ResponseEntity<?> getTransactionsBySoirceBankAccountId(@PathVariable String id) {
    Optional<List<TransactionDTO>> optionalTransactionDTOS =
        transactionService.getAllTransactionsByBankAccountId(id);
    if (optionalTransactionDTOS.isPresent()) {
      return new ResponseEntity<>(optionalTransactionDTOS, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  // http://localhost:8080/transaction/create
  @PostMapping(
      value = "/create",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      description =
          "Creating a new Transaction. If you are using swagger UI, remove the ID, and the timestamp from the Request Body.")
  public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
    Optional<TransactionDTO> optionalTransactionDTO =
        transactionService.createTransaction(transaction);
    if (optionalTransactionDTO.isPresent()) {
      return new ResponseEntity<>(optionalTransactionDTO, HttpStatus.CREATED);
    }
    return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_CONTENT);
  }
}
