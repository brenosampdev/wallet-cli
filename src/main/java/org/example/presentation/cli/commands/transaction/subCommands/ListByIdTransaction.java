package org.example.presentation.cli.commands.transaction.subCommands;

import java.util.List;
import java.util.UUID;

import org.example.application.services.TransactionService;
import org.example.domain.entities.TransactionEntity;
import org.example.presentation.cli.commands.transaction.validation.ListByIdTransactionValidation;
import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.interfaces.ValidationRule;

public class ListByIdTransaction implements Command {
  private final TransactionService service;

  public ListByIdTransaction(TransactionService service) {
    this.service = service;
  }

  @Override
  public String name() {
    return "listById";
  }

  @Override
  public String info() {
    return """
        List a transaction by id.
        Usage: wallet transaction listById --id <uuid>
        """;
  }

  @Override
  public List<ValidationRule> specArgs(CommandContext context) {
    return List.of(ListByIdTransactionValidation.INSTANCE);
  }

  @Override
  public void execute(CommandContext context) {
    TransactionEntity tx = service.findById(UUID.fromString(context.get("id")));

    System.out.println("ID: " + tx.getId());
    System.out.println("Tipo: " + tx.getType());
    System.out.println("Valor: " + tx.getAmount());
    System.out.println("Data/Hora: " + tx.getDateTime());
    System.out.println("Descrição: " + tx.getDescription());
    System.out.println("Parcelas: " + tx.getInstallments());
    System.out.println("Categoria ID: " + tx.getCategoryId());
  }
}
