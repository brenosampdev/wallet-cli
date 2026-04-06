package org.example.application.mappers;

import org.example.application.dtos.category.CategoryCreateDto;
import org.example.application.dtos.category.CategoryUpdateDto;
import org.example.domain.entities.CategoryEntity;

public final class CategoryMapper {
  private CategoryMapper() {}

  public static CategoryEntity toEntity(CategoryCreateDto dto) {
    return new CategoryEntity(
      dto.title(),
      dto.description()
    );
  }

  public static CategoryEntity toEntity(CategoryUpdateDto dto, CategoryEntity current) {
    return new CategoryEntity(
      current.getId(),
      dto.newTitle(),
      dto.description()
    );
  }
}
