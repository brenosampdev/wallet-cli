package org.example.domain.repositories;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.example.domain.entities.CategoryEntity;

public interface ICategoryRepository {
  CategoryEntity insert(CategoryEntity category) throws IOException;

  Optional<CategoryEntity> findByName(String name) throws IOException;

  List<CategoryEntity> findAll() throws IOException;

  void update(CategoryEntity category) throws IOException;

  void deleteById(UUID id) throws IOException;
}
