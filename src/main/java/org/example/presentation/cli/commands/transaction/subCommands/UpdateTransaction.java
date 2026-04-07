package org.example.presentation.cli.commands.transaction.subCommands;

import java.util.List;

import org.example.application.dtos.transaction.TransactionUpdateDto;
import org.example.application.services.TransactionService;
import org.example.domain.entities.TransactionEntity;
import org.example.presentation.cli.commands.transaction.dtos.TransactionUpdateInputDto;
import org.example.presentation.cli.commands.transaction.mappers.TransactionInputMapper;
import org.example.presentation.cli.commands.transaction.validation.UpdateTransactionValidation;
import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.interfaces.ValidationRule;

public class UpdateTransaction implements Command {
  private final TransactionService service;

  public UpdateTransaction(TransactionService service) {
    this.service = service;
  }

  @Override
  public String name() {
    return "update";
  }

  @Override
  public String info() {
    return """
        Update a transaction.
        Usage: wallet transaction update --id <uuid> --amount 700.00 --description "Buy PS5"
        """;
  }

  @Override
  public List<ValidationRule> specArgs(CommandContext context) {
    return List.of(UpdateTransactionValidation.INSTANCE);
  }

  @Override
  public void execute(CommandContext context) {
    TransactionUpdateInputDto input = new TransactionUpdateInputDto(
      context.get("id"),
      context.get("type"),
      context.get("amount"),
      context.get("dateTime"),
      context.get("description"),
      context.get("installments"),
      context.get("category")
    );

    TransactionUpdateDto dto = TransactionInputMapper.toUpdateDto(input);
    TransactionEntity updated = service.update(dto);

    System.out.println("Transação atualizada com sucesso!");
    System.out.println("ID: " + updated.getId());
    System.out.println("Tipo: " + updated.getType());
    System.out.println("Valor: " + updated.getAmount());
    System.out.println("Data/Hora: " + updated.getDateTime());
    System.out.println("Descrição: " + updated.getDescription());
    System.out.println("Parcelas: " + updated.getInstallments());
  }
}
