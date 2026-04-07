package org.example.presentation.cli.commands.transaction.subCommands;

import java.util.List;
import java.util.UUID;

import org.example.application.services.TransactionService;
import org.example.presentation.cli.commands.transaction.validation.RemoveTransactionValidation;
import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.interfaces.ValidationRule;

public class RemoveTransaction implements Command {
  private final TransactionService service;

  public RemoveTransaction(TransactionService service) {
    this.service = service;
  }

  @Override
  public String name() {
    return "delete";
  }

  @Override
  public String info() {
    return """
        Delete a transaction.
        Usage: wallet transaction delete --id <uuid>
               wallet transaction delete --category <nome>
        """;
  }

  @Override
  public List<ValidationRule> specArgs(CommandContext context) {
    return List.of(RemoveTransactionValidation.INSTANCE);
  }

  @Override
  public void execute(CommandContext context) {
    if (context.has("id")) {
      service.delete(UUID.fromString(context.get("id")));
      System.out.println("Transação removida com sucesso!");
      return;
    }

    service.deleteByCategory(context.get("category"));
    System.out.println("Transações da categoria removidas com sucesso!");
  }
}
