package org.example.infrastructure.repositories;

import java.io.IOException;

import org.example.domain.repositories.ITransactionRepository;
import org.example.infrastructure.JsonStorage;
import org.example.infrastructure.StorageCollection;

public class TransactionRepository implements ITransactionRepository{
  private JsonStorage storage;
  private static final StorageCollection COLLECTION = StorageCollection.TRANSACTIONS;

  public TransactionRepository(JsonStorage storage){
    this.storage = storage;
  }

  public void insert(Object dto) throws IOException {
    this.storage.write(COLLECTION, dto);
  }

}
