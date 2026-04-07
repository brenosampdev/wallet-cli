package org.example.presentation.cli.commands.transaction.validation;

import java.util.ArrayList;
import java.util.List;

import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.ValidationRule;

public class RemoveTransactionValidation implements ValidationRule {
  public static final RemoveTransactionValidation INSTANCE = new RemoveTransactionValidation();

  private RemoveTransactionValidation() {}

  @Override
  public List<String> validate(CommandContext ctx) {
    List<String> messages = new ArrayList<>();

    boolean hasId = ctx.has("id") && ctx.get("id") != null && !ctx.get("id").trim().isEmpty() && !"true".equalsIgnoreCase(ctx.get("id"));
    boolean hasCategory = ctx.has("category") && ctx.get("category") != null && !ctx.get("category").trim().isEmpty() && !"true".equalsIgnoreCase(ctx.get("category"));

    if (!hasId && !hasCategory) {
      messages.add("Informe --id <uuid> para deletar por id, ou --category <nome> para deletar por categoria");
    }

    if (hasId && hasCategory) {
      messages.add("Informe apenas --id ou --category, não ambos");
    }

    return messages;
  }
}
