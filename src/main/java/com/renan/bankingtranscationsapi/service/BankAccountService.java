package com.renan.bankingtranscationsapi.service;

import com.renan.bankingtranscationsapi.dto.BankAccountDTO;
import com.renan.bankingtranscationsapi.exception.*;
import com.renan.bankingtranscationsapi.mapper.BankAccountMapper;
import com.renan.bankingtranscationsapi.model.BankAccount;
import org.springframework.stereotype.Service;
import com.renan.bankingtranscationsapi.repository.BankAccountRepository;

import java.util.Optional;

@Service
public class BankAccountService {

  private final BankAccountRepository bankAccountRepository;

  public BankAccountService(BankAccountRepository bankAccountRepository) {
    this.bankAccountRepository = bankAccountRepository;
  }

  public Optional<BankAccountDTO> getAccountById(String id) {
    try {
      Long convertedId = Long.parseLong(id);
      if (convertedId < 0) {
        throw new PassingNegativeValueException("ID must be above 0!");
      }
      Optional<BankAccount> account = bankAccountRepository.findById(convertedId);
      Optional<BankAccountDTO> optionalBankAccountDTO = Optional.empty();
      if (account.isEmpty()) {
        throw new EntityNotFoundException("Source or Destination Bank Account not found");
      }
      optionalBankAccountDTO =
          Optional.ofNullable(BankAccountMapper.INSTANCE.entityToDto(account.get()));
      return optionalBankAccountDTO;
    } catch (NumberFormatException e) {
      throw new InvalidIDException("Please, only type the Bank Account ID as an integer.");
    }
  }

  public Optional<BankAccountDTO> createAccount(BankAccount account) {
    try {
      Optional<BankAccountDTO> optionalBankAccountDTO = Optional.empty();
      if (account.getBalance() < 100) {
        throw new InitialBalanceBelow100Exception("The initial balance must be $100 or above.");
      }
      if (account.getSinNumber() < 1) {
        throw new SinNumberBelow1Exception("The SIN must be above zero (0).");
      }
      Optional<BankAccount> accountOptional =
          Optional.ofNullable(findBankAccountBySIN(account.getSinNumber()));
      if (accountOptional.isEmpty()) {
        BankAccount acc = bankAccountRepository.save(account);
        optionalBankAccountDTO = Optional.ofNullable(BankAccountMapper.INSTANCE.entityToDto(acc));
        return optionalBankAccountDTO;
      }
      throw new BankAccountAlreadyExistsException("There is a bank account with the same SIN.");
    } catch (NumberFormatException e) {
      throw new InvalidIDException("Please, only type the SIN Number as an integer.");
    }
  }

  public void updateAccountBalance(
      BankAccountDTO sourceAccount, BankAccountDTO destinationAccount, Double transactionValue) {
    Double newSourceBalance = sourceAccount.balance() - transactionValue;
    Double newDestinationbalance = destinationAccount.balance() + transactionValue;
    bankAccountRepository.updateBankAccountBalance(sourceAccount.id(), newSourceBalance);
    bankAccountRepository.updateBankAccountBalance(destinationAccount.id(), newDestinationbalance);
  }

  private BankAccount findBankAccountBySIN(Long sinNumber) {
    BankAccount acc = bankAccountRepository.findBankAccountBySinNumber(sinNumber);
    return acc;
  }
}
