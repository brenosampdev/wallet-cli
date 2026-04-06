package org.example.presentation.cli.commands.category.dtos;

public record CategoryUpdateInputDto(
  String oldTitle,
  String newTitle,
  String description
) {
}
