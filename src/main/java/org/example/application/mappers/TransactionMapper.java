package org.example.application.mappers;

import org.example.application.dtos.transaction.TransactionCreateDto;
import org.example.domain.entities.TransactionEntity;

public class TransactionMapper {
  private TransactionMapper() {}

  public static TransactionEntity toEntity(TransactionCreateDto dto) {
    return new TransactionEntity(
        dto.type(),
        dto.amount(),
        dto.dateTime(),
        dto.description(),
        dto.installments()
    );
  }

  public static TransactionCreateDto toDto(TransactionEntity entity) {
    return new TransactionCreateDto(
        entity.getType(),
        entity.getAmount(),
        entity.getDateTime(),
        entity.getDescription(),
        entity.getInstallments()
    );
  }
}
