package org.example.presentation.cli.commands.transaction.validation;

import java.util.ArrayList;
import java.util.List;

import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.ValidationRule;

public class UpdateTransactionValidation implements ValidationRule {
  public static final UpdateTransactionValidation INSTANCE = new UpdateTransactionValidation();

  private UpdateTransactionValidation() {}

  @Override
  public List<String> validate(CommandContext ctx) {
    List<String> messages = new ArrayList<>();

    String id = ctx.get("id");
    if (id == null || id.trim().isEmpty() || "true".equalsIgnoreCase(id)) {
      messages.add("O argumento --id requer um valor (ex: --id 550e8400-e29b-41d4-a716-446655440000)");
    }

    return messages;
  }
}
