package org.example.presentation.cli.commands.category.validation;

import java.util.ArrayList;
import java.util.List;

import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.ValidationRule;

public class RemoveCategoryValidation implements ValidationRule {
  public static final RemoveCategoryValidation INSTANCE = new RemoveCategoryValidation();

  private RemoveCategoryValidation() {}

  @Override
  public List<String> validate(CommandContext ctx) {
    List<String> messages = new ArrayList<>();

    String name = ctx.get("name");
    if (name == null || name.trim().isEmpty() || "true".equalsIgnoreCase(name)) {
      messages.add("O argumento --name requer um valor (ex: --name \"Minha categoria\")");
    }

    return messages;
  }
}
