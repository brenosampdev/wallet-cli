package org.example.infrastructure.dtos.transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record TransactionListDto(List<TransactionDto> transactions) {

  public TransactionListDto {
    transactions = (transactions == null) ? new ArrayList<>() : new ArrayList<>(transactions);
  }

  public static TransactionListDto empty() {
    return new TransactionListDto(List.of());
  }

  public TransactionListDto add(TransactionDto dto) {
    List<TransactionDto> copy = new ArrayList<>(this.transactions);
    copy.add(dto);
    return new TransactionListDto(copy);
  }

  public TransactionListDto replace(TransactionDto dto) {
    List<TransactionDto> copy = new ArrayList<>(this.transactions);
    for (int i = 0; i < copy.size(); i++) {
      if (copy.get(i).id().equals(dto.id())) {
        copy.set(i, dto);
        return new TransactionListDto(copy);
      }
    }
    throw new IllegalStateException("Transação não encontrada para update: " + dto.id());
  }

  public TransactionListDto removeById(UUID id) {
    List<TransactionDto> copy = new ArrayList<>(this.transactions);
    copy.removeIf(t -> t.id().equals(id));
    return new TransactionListDto(copy);
  }
}
