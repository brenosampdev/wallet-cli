package org.example.infrastructure.repositories;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.example.domain.entities.TransactionEntity;
import org.example.domain.enums.transactions.TransactionType;
import org.example.domain.repositories.ITransactionRepository;
import org.example.infrastructure.JsonStorage;
import org.example.infrastructure.StorageCollection;
import org.example.infrastructure.dtos.transaction.TransactionDto;
import org.example.infrastructure.dtos.transaction.TransactionListDto;
import org.example.infrastructure.mappers.TransactionMapper;

public class TransactionRepository implements ITransactionRepository {
  private final JsonStorage storage;
  private static final StorageCollection COLLECTION = StorageCollection.TRANSACTIONS;

  public TransactionRepository(JsonStorage storage) {
    this.storage = storage;
  }

  private TransactionListDto readList() throws IOException {
    return storage.read(COLLECTION, TransactionListDto.class).orElse(TransactionListDto.empty());
  }

  @Override
  public TransactionEntity insert(TransactionEntity transaction) throws IOException {
    TransactionDto dto = TransactionMapper.toDto(transaction);
    TransactionListDto updated = readList().add(dto);
    storage.write(COLLECTION, updated);
    return transaction;
  }

  @Override
  public void update(TransactionEntity transaction) throws IOException {
    TransactionDto dto = TransactionMapper.toDto(transaction);
    TransactionListDto updated = readList().replace(dto);
    storage.write(COLLECTION, updated);
  }

  @Override
  public List<TransactionEntity> findAll() throws IOException {
    return readList().transactions().stream()
        .map(TransactionMapper::toEntity)
        .toList();
  }

  @Override
  public void deleteById(UUID id) throws IOException {
    TransactionListDto updated = readList().removeById(id);
    storage.write(COLLECTION, updated);
  }

  @Override
  public Optional<TransactionEntity> findById(UUID id) throws IOException {
    return readList().transactions().stream()
        .filter(t -> t.id().equals(id))
        .findFirst()
        .map(TransactionMapper::toEntity);
  }

  @Override
  public List<TransactionEntity> findByType(TransactionType type) throws IOException {
    return readList().transactions().stream()
        .filter(t -> t.type() == type)
        .map(TransactionMapper::toEntity)
        .toList();
  }

  @Override
  public List<TransactionEntity> findByPeriod(Instant startInclusive, Instant endInclusive) throws IOException {
    return readList().transactions().stream()
        .filter(t -> !t.dateTime().isBefore(startInclusive) && !t.dateTime().isAfter(endInclusive))
        .map(TransactionMapper::toEntity)
        .toList();
  }
}
