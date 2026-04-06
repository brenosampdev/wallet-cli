package org.example.infrastructure.repositories;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.example.domain.entities.CategoryEntity;
import org.example.domain.repositories.ICategoryRepository;
import org.example.infrastructure.JsonStorage;
import org.example.infrastructure.StorageCollection;
import org.example.infrastructure.dtos.category.CategoryDto;
import org.example.infrastructure.dtos.category.CategoryListDto;
import org.example.infrastructure.mappers.CategoryMapper;

public class CategoryRepository implements ICategoryRepository{
  private final JsonStorage storage;
  private static final StorageCollection COLLECTION = StorageCollection.CATEGORIES;

  public CategoryRepository(JsonStorage storage) {
    this.storage = storage;
  }

  private CategoryListDto readList() throws IOException {
    return storage.read(COLLECTION, CategoryListDto.class).orElse(CategoryListDto.empty());
  }

  @Override
  public CategoryEntity insert(CategoryEntity category) throws IOException {
    CategoryDto dto = CategoryMapper.toDto(category);
    Optional<CategoryDto> isExist = readList()
      .categories()
      .stream()
      .filter(c -> c.title().equals(category.getTitle()))
      .findFirst();

    if(isExist.isPresent()){
      throw new IllegalArgumentException("Category already exists: " + category.getTitle());
    }

    CategoryListDto updated = readList().add(dto);
    storage.write(COLLECTION, updated);
    
    return category;
  }

  @Override
  public List<CategoryEntity> findAll() throws IOException {
    return readList()
      .categories()
      .stream()
      .map(CategoryMapper::toEntity)
      .toList();
  }

  @Override
  public Optional<CategoryEntity> findByName(String name) throws IOException {
    return readList()
      .categories()
      .stream()
      .filter(c -> c.title().equals(name))
      .findFirst()
      .map(CategoryMapper::toEntity);
  }

  @Override
  public void deleteById(UUID id) throws IOException {
    CategoryListDto updated = readList().removeById(id);
    storage.write(COLLECTION, updated);
  }

  @Override
  public void update(CategoryEntity category) throws IOException {
    CategoryDto dto = CategoryMapper.toDto(category);
    CategoryListDto updated = readList().replace(dto);
    storage.write(COLLECTION, updated);
  }
}
