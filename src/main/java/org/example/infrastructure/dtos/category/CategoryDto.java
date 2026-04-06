package org.example.infrastructure.dtos.category;

import java.util.UUID;

public record CategoryDto(
  UUID id,
  String title,
  String description
){
  public CategoryDto {
    id = (id == null) ? UUID.randomUUID() : id;
    title = (title == null) ? "" : title.trim();
    description = (description == null) ? "" : description;
  }
}
