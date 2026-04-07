package org.example.presentation.cli.commands.transaction.subCommands;

import java.util.List;
import java.util.UUID;

import org.example.application.services.TransactionService;
import org.example.domain.entities.TransactionEntity;
import org.example.presentation.cli.commands.transaction.validation.ListByIdTransactionValidation;
import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.interfaces.ValidationRule;

public class ListAllTransaction implements Command {
  private final TransactionService service;

  public ListAllTransaction(TransactionService service) {
    this.service = service;
  }

  @Override
  public String name() {
    return "list";
  }

  @Override
  public String info() {
    return """
        List transactions.
        Usage: wallet transaction list
               wallet transaction list --id <uuid>
        """;
  }

  @Override
  public List<ValidationRule> specArgs(CommandContext context) {
    if (context.has("id")) {
      return List.of(ListByIdTransactionValidation.INSTANCE);
    }
    return List.of();
  }

  @Override
  public void execute(CommandContext context) {
    if (context.has("id")) {
      TransactionEntity tx = service.findById(UUID.fromString(context.get("id")));
      printTransaction(tx);
      return;
    }

    List<TransactionEntity> transactions = service.findAll();

    if (transactions.isEmpty()) {
      System.out.println("Nenhuma transação encontrada.");
      return;
    }

    System.out.println("Transações:\n");
    for (TransactionEntity tx : transactions) {
      printTransaction(tx);
      System.out.println();
    }
  }

  private void printTransaction(TransactionEntity tx) {
    System.out.println("ID: " + tx.getId());
    System.out.println("Tipo: " + tx.getType());
    System.out.println("Valor: " + tx.getAmount());
    System.out.println("Data/Hora: " + tx.getDateTime());
    System.out.println("Descrição: " + tx.getDescription());
    System.out.println("Parcelas: " + tx.getInstallments());
    System.out.println("Categoria ID: " + tx.getCategoryId());
  }
}
