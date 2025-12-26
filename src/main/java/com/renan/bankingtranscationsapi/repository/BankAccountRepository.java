package com.renan.bankingtranscationsapi.repository;

import com.renan.bankingtranscationsapi.dto.BankAccountDTO;
import com.renan.bankingtranscationsapi.model.BankAccount;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

  @Query(value = "SELECT * FROM accounts WHERE sin_number = :sinNumber", nativeQuery = true)
  public BankAccount findBankAccountBySinNumber(@Param("sinNumber") Long sinNumber);

  @Transactional
  @Modifying
  @Query(value = "UPDATE accounts SET balance = :balance WHERE id = :id", nativeQuery = true)
  public void updateBankAccountBalance(@Param("id") Long id, @Param("balance") Double balance);
}
