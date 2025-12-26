package com.renan.bankingtranscationsapi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private LocalDateTime timestamp;

  @Column(name = "source_bank_account_id")
  private Long sourceBankAccountId;

  @Column(name = "destination_bank_account_id")
  private Long destinationBankAccountId;

  @Column private Double transactionValue;

  public Transaction(
      Long sourceBankAccountId, Long destinationBankAccountId, Double transactionValue) {
    this.sourceBankAccountId = sourceBankAccountId;
    this.destinationBankAccountId = destinationBankAccountId;
    this.transactionValue = transactionValue;
  }

  public Transaction() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public Long getSourceBankAccountId() {
    return sourceBankAccountId;
  }

  public void setSourceBankAccountId(Long sourceBankAccountId) {
    this.sourceBankAccountId = sourceBankAccountId;
  }

  public Long getDestinationBankAccountId() {
    return destinationBankAccountId;
  }

  public void setDestinationBankAccountId(Long destinationBankAccountId) {
    this.destinationBankAccountId = destinationBankAccountId;
  }

  public Double getTransactionValue() {
    return transactionValue;
  }

  public void setTransactionValue(Double transactionValue) {
    this.transactionValue = transactionValue;
  }
}
