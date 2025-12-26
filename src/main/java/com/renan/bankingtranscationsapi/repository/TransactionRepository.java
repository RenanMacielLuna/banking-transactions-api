package com.renan.bankingtranscationsapi.repository;

import com.renan.bankingtranscationsapi.dto.TransactionDTO;
import com.renan.bankingtranscationsapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  @Query(
      value =
          "SELECT * FROM transactions WHERE source_bank_account_id = :sourceId OR destination_bank_account_id = :sourceId",
      nativeQuery = true)
  public List<Transaction> getAllTransactionsBySourceOrDestinationBankAccountId(
      @Param("sourceId") Long sourceId);
}
