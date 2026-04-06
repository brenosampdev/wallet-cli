package org.example.infrastructure.mappers;

import org.example.domain.entities.CategoryEntity;
import org.example.infrastructure.dtos.category.CategoryDto;

public final class CategoryMapper {
  private CategoryMapper() {}

  public static CategoryEntity toEntity(CategoryDto dto) {
    return new CategoryEntity(
        dto.id(),
        dto.title(),
        dto.description()
    );
  }

  public static CategoryDto toDto(CategoryEntity entity) {
    return new CategoryDto(
        entity.getId(),
        entity.getTitle(),
        entity.getDescription()
    );
  }
}
