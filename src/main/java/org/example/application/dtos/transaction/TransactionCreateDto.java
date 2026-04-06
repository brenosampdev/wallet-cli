package org.example.application.dtos.transaction;

import java.math.BigDecimal;
import java.time.Instant;
import org.example.domain.enums.transactions.TransactionType;

public record TransactionCreateDto(
  TransactionType type,
  BigDecimal amount,
  Instant dateTime,
  String description,
  Integer installments,
  String categoryName
) {
  public TransactionCreateDto {
    type = (type == null) ? TransactionType.INPUT : type;
    amount = (amount == null) ? BigDecimal.ZERO : amount;
    dateTime = (dateTime == null) ? Instant.now() : dateTime;
    description = (description == null) ? "" : description;
    installments = (installments == null || installments <= 0) ? 1 : installments;
    categoryName = (categoryName == null) ? "" : categoryName.trim();
  }
}
