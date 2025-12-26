package com.renan.bankingtranscationsapi.dto;

import org.jspecify.annotations.NonNull;

import java.time.LocalDateTime;

public record TransactionDTO(
    @NonNull LocalDateTime timestamp,
    @NonNull Long sourceBankAccountId,
    @NonNull Long destinationBankAccountId,
    @NonNull Double transactionValue) {}
