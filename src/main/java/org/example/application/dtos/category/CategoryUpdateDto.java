package org.example.application.dtos.category;

public record CategoryUpdateDto(
  String oldTitle,
  String newTitle,
  String description
) {
  public CategoryUpdateDto {
    oldTitle = (oldTitle == null) ? "" : oldTitle.trim();
    newTitle = (newTitle == null) ? "" : newTitle.trim();
    description = (description == null) ? "" : description;
  }
}
