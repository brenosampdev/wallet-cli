

package org.example.application.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.example.application.dtos.category.CategoryCreateDto;
import org.example.application.dtos.category.CategoryUpdateDto;
import org.example.application.mappers.CategoryMapper;
import org.example.domain.entities.CategoryEntity;
import org.example.domain.repositories.ICategoryRepository;

public class CategoryService {
  private final ICategoryRepository categoryRepository;

  public CategoryService( ICategoryRepository categoryRepository){
    this.categoryRepository = categoryRepository;
  }

  private String normalizeName(String name) {
    return name == null ? "" : name.toLowerCase();
  }

  private Optional<CategoryEntity> findByNameNormalized(String name) throws IOException {
    String normalized = normalizeName(name);
    return categoryRepository
      .findAll()
      .stream()
      .filter(category -> normalizeName(category.getTitle()).equals(normalized))
      .findFirst();
  }

  public CategoryEntity create(CategoryCreateDto dto) {
    try {
      String normalizedTitle = normalizeName(dto.title());
      if (normalizedTitle.isEmpty()) {
        throw new RuntimeException("nome da categoria é obrigatório");
      }

      Optional<CategoryEntity> existing = findByNameNormalized(dto.title());
      if (existing.isPresent()) {
        throw new RuntimeException("categoria já existe");
      }

      CategoryEntity category = CategoryMapper.toEntity(dto);
      return categoryRepository.insert(category);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<CategoryEntity> findByName(String name) {
    try {
      return findByNameNormalized(name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<CategoryEntity> findAll() {
    try {
      return categoryRepository.findAll();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void delete(String name) {
    try {
      CategoryEntity category = findByNameNormalized(name)
        .orElseThrow(() -> new RuntimeException("categoria não encontrada"));

      categoryRepository.deleteById(category.getId());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public CategoryEntity update(CategoryUpdateDto dto) {
    try {
      String normalizedTitle = normalizeName(dto.newTitle());
      if (normalizedTitle.isEmpty()) {
        throw new RuntimeException("nome da categoria é obrigatório");
      }

      CategoryEntity current = findByNameNormalized(dto.oldTitle())
        .orElseThrow(() -> new RuntimeException("categoria não encontrada"));

      Optional<CategoryEntity> existing = findByNameNormalized(dto.newTitle());
      if (existing.isPresent() && !existing.get().getId().equals(current.getId())) {
        throw new RuntimeException("categoria já existe");
      }

      CategoryEntity updated = CategoryMapper.toEntity(dto, current);

      categoryRepository.update(updated);
      return updated;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}