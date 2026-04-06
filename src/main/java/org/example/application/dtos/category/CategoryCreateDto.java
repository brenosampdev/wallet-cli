package org.example.application.dtos.category;

public record CategoryCreateDto(
  String title,
  String description
) {
  public CategoryCreateDto {
    title = (title == null) ? "" : title.trim();
    description = (description == null) ? "" : description;
  }
}
