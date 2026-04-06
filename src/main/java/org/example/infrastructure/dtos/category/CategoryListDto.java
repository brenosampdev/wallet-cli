package org.example.infrastructure.dtos.category;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record CategoryListDto(List<CategoryDto> categories) {

  public CategoryListDto {
    categories = (categories == null) ? new ArrayList<>() : new ArrayList<>(categories);
  }

  public static CategoryListDto empty() {
    return new CategoryListDto(List.of());
  }

  public CategoryListDto add(CategoryDto dto) {
    List<CategoryDto> copy = new ArrayList<>(this.categories);
    copy.add(dto);
    return new CategoryListDto(copy);
  }

  public CategoryListDto replace(CategoryDto dto) {
    List<CategoryDto> copy = new ArrayList<>(this.categories);
    for (int i = 0; i < copy.size(); i++) {
      if (copy.get(i).id().equals(dto.id())) {
        copy.set(i, dto);
        return new CategoryListDto(copy);
      }
    }
    throw new IllegalStateException("Categoria não encontrada para update: " + dto.id());
  }

  public CategoryListDto removeById(UUID id) {
    List<CategoryDto> copy = new ArrayList<>(this.categories);
    copy.removeIf(c -> c.id().equals(id));
    return new CategoryListDto(copy);
  }
}
