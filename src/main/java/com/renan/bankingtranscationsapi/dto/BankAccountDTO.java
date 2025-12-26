package com.renan.bankingtranscationsapi.dto;

import org.jspecify.annotations.NonNull;

public record BankAccountDTO(@NonNull Long id, @NonNull String fullName, @NonNull Double balance) {}
