package org.example.presentation.cli.commands.category.validation;

import java.util.ArrayList;
import java.util.List;

import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.ValidationRule;

public class UpdateCategoryValidation implements ValidationRule {
  public static final UpdateCategoryValidation INSTANCE = new UpdateCategoryValidation();

  private UpdateCategoryValidation() {}

  @Override
  public List<String> validate(CommandContext ctx) {
    List<String> messages = new ArrayList<>();

    String oldTitle = ctx.get("oldTitle");
    if (oldTitle == null || oldTitle.trim().isEmpty() || "true".equalsIgnoreCase(oldTitle)) {
      messages.add("O argumento --oldTitle requer um valor (ex: --oldTitle \"Categoria atual\")");
    }

    String newTitle = ctx.get("newTitle");
    if (newTitle == null || newTitle.trim().isEmpty() || "true".equalsIgnoreCase(newTitle)) {
      messages.add("O argumento --newTitle requer um valor (ex: --newTitle \"Nova categoria\")");
    }

    if (ctx.has("description")) {
      String description = ctx.get("description");
      if (description == null || description.trim().isEmpty() || "true".equalsIgnoreCase(description)) {
        messages.add("Se informado, --description deve receber um texto válido (ex: --description \"texto\")");
      }
    }

    return messages;
  }
}
