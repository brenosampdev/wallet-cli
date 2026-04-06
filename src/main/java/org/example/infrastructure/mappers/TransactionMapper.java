package org.example.infrastructure.mappers;

import org.example.domain.entities.TransactionEntity;
import org.example.infrastructure.dtos.transaction.TransactionDto;

public final class TransactionMapper {
  private TransactionMapper() {}

  public static TransactionEntity toEntity(TransactionDto dto) {
    return new TransactionEntity(
        dto.id(),
        dto.type(),
        dto.amount(),
        dto.dateTime(),
        dto.description(),
        dto.installments(),
        dto.categoryId()
    );
  }

  public static TransactionDto toDto(TransactionEntity entity) {
    return new TransactionDto(
        entity.getId(),
        entity.getType(),
        entity.getAmount(),
        entity.getDateTime(),
        entity.getDescription(),
        entity.getInstallments(),
        entity.getCategoryId()
    );
  }
}