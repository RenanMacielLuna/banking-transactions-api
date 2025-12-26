package com.renan.bankingtranscationsapi.service;

import com.renan.bankingtranscationsapi.dto.BankAccountDTO;
import com.renan.bankingtranscationsapi.dto.TransactionDTO;
import com.renan.bankingtranscationsapi.exception.*;
import com.renan.bankingtranscationsapi.mapper.TransactionMapper;
import com.renan.bankingtranscationsapi.model.Transaction;
import com.renan.bankingtranscationsapi.repository.BankAccountRepository;
import com.renan.bankingtranscationsapi.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

  private final TransactionRepository transactionRepository;
  private final BankAccountService bankAccountService;

  public TransactionService(
      TransactionRepository transactionRepository, BankAccountService bankAccountService) {
    this.transactionRepository = transactionRepository;
    this.bankAccountService = bankAccountService;
  }

  public Optional<List<TransactionDTO>> getAllTransactionsByBankAccountId(String sourceId) {
    Long convertedSourceId = Long.parseLong(sourceId);
    List<Transaction> transactionsList = new ArrayList<>();
    Optional<List<TransactionDTO>> resultList = Optional.of(new ArrayList<>());
    if (convertedSourceId <= 0) {
      throw new PassingNegativeValueException("Bank Account ID must be above 0!");
    }
    transactionsList =
        transactionRepository.getAllTransactionsBySourceOrDestinationBankAccountId(
            convertedSourceId);
    if (transactionsList.isEmpty()) {
      throw new EntityNotFoundException(
          "There are no transactions for the ID: " + convertedSourceId);
    }
    for (Transaction transaction : transactionsList) {
      TransactionDTO transactionDTO = TransactionMapper.INSTANCE.entityToDto(transaction);
      resultList.get().add(transactionDTO);
    }
    return resultList;
  }

  public Optional<TransactionDTO> createTransaction(Transaction transaction) {
    Optional<TransactionDTO> result = Optional.empty();
    try {
      if (transaction.getTransactionValue() <= 0) {
        throw new PassingNegativeValueException("Please, the transaction value must be above 0");
      }
      if (transaction.getSourceBankAccountId().equals(transaction.getDestinationBankAccountId())) {
        throw new SameSourceAndDestinationBankAccountIdException(
            "The source and destination ID MUST be different");
      }
      Optional<BankAccountDTO> source =
          bankAccountService.getAccountById("" + transaction.getSourceBankAccountId());
      Optional<BankAccountDTO> destination =
          bankAccountService.getAccountById("" + transaction.getDestinationBankAccountId());
      if (source.isPresent() && destination.isPresent()) {
        if (source.get().balance() < transaction.getTransactionValue()) {
          throw new InsufficientFundsException(
              "The transaction value MUST be less than or equal to" + source.get().balance());
        }
        transaction.setTimestamp(LocalDateTime.now());
        bankAccountService.updateAccountBalance(
            source.get(), destination.get(), transaction.getTransactionValue());
        transactionRepository.save(transaction);
        result = Optional.ofNullable(TransactionMapper.INSTANCE.entityToDto(transaction));
      }
      return result;
    } catch (NumberFormatException e) {
      throw new InvalidBalanceException("The balance MUST be a number.");
    }
  }
}
