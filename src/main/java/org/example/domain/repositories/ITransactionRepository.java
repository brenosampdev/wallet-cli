package org.example.domain.repositories;

import org.example.domain.entities.TransactionEntity;
import org.example.domain.enums.transactions.TransactionType;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITransactionRepository {

  TransactionEntity insert(TransactionEntity transaction) throws IOException;

  Optional<TransactionEntity> findById(UUID id) throws IOException;

  List<TransactionEntity> findAll() throws IOException;

  List<TransactionEntity> findByType(TransactionType type) throws IOException;

  List<TransactionEntity> findByPeriod(Instant startInclusive, Instant endInclusive) throws IOException;

  void update(TransactionEntity transaction) throws IOException;

  void deleteById(UUID id) throws IOException;
}
