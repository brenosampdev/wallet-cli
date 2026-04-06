package org.example.presentation.cli.commands.category.mappers;

import org.example.application.dtos.category.CategoryCreateDto;
import org.example.application.dtos.category.CategoryUpdateDto;
import org.example.presentation.cli.commands.category.dtos.CategoryCreateInputDto;
import org.example.presentation.cli.commands.category.dtos.CategoryUpdateInputDto;

public final class CategoryInputMapper {
  private CategoryInputMapper() {}

  public static CategoryCreateDto toCreateDto(CategoryCreateInputDto input) {
    return new CategoryCreateDto(
      input.title(),
      input.description()
    );
  }

  public static CategoryUpdateDto toUpdateDto(CategoryUpdateInputDto input) {
    return new CategoryUpdateDto(
      input.oldTitle(),
      input.newTitle(),
      input.description()
    );
  }
}
