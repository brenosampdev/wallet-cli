package org.example.application.mappers;

import org.example.application.dtos.transaction.TransactionCreateDto;
import org.example.application.dtos.transaction.TransactionUpdateDto;
import org.example.domain.entities.TransactionEntity;

import java.util.UUID;

public final class TransactionMapper {
  private TransactionMapper() {}

  public static TransactionEntity toEntity(TransactionCreateDto dto, UUID categoryId) {
    return new TransactionEntity(
      dto.type(),
      dto.amount(),
      dto.dateTime(),
      dto.description(),
      dto.installments(),
      categoryId
    );
  }

  public static TransactionEntity toEntity(TransactionUpdateDto dto, TransactionEntity current, UUID categoryId) {
    return new TransactionEntity(
      current.getId(),
      dto.type(),
      dto.amount(),
      dto.dateTime(),
      dto.description(),
      dto.installments(),
      categoryId
    );
  }
}
