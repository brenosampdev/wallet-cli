package org.example.infrastructure.dtos.transaction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import org.example.domain.enums.transactions.TransactionType;

public record TransactionDto(
  UUID id,
  TransactionType type,
  BigDecimal amount,
  Instant dateTime,
  String description,
  Integer installments,
  UUID categoryId
){
  public TransactionDto {
    id = (id == null) ? UUID.randomUUID() : id;
    type = (type == null) ? TransactionType.INPUT : type;
    amount = (amount == null) ? BigDecimal.ZERO : amount;
    dateTime = (dateTime == null) ? Instant.now() : dateTime;
    description = (description == null) ? "" : description;
    installments = (installments == null || installments <= 0) ? 1 : installments;
  }
}
