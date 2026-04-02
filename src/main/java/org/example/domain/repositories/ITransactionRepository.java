package org.example.domain.repositories;

import java.io.IOException;

public interface ITransactionRepository {
 void insert(Object dto) throws IOException;
}
