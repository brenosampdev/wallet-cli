package org.example.domain.entities;

import java.util.Objects;
import java.util.UUID;

public class CategoryEntity {
  private final UUID id;
  private final String title;
  private String description = "";


  public CategoryEntity(
      UUID id,
      String title,
      String description
  ) {
    this.id = Objects.requireNonNull(id, "id obrigatório");
    this.title = Objects.requireNonNull(title, "titulo obrigatorio");
    this.description = description;
  }

  public CategoryEntity(
      String title,
      String description
  ){
    this(UUID.randomUUID(), title, description);
  }

  public UUID getId() { return id; }
  public String getTitle() { return title; }
  public String getDescription() { return description; }
}
