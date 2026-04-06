package org.example.presentation.cli.commands.transaction.validation;

import java.util.ArrayList;
import java.util.List;

import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.ValidationRule;

public class AddTransactionValidation implements ValidationRule {
  public static final AddTransactionValidation INSTANCE = new AddTransactionValidation();

  private AddTransactionValidation() {}

  @Override
  public List<String> validate(CommandContext ctx) {
    List<String> messages = new ArrayList<>();

    String type = ctx.get("type");
    if (type == null || type.trim().isEmpty() || "true".equalsIgnoreCase(type)) {
      messages.add("O argumento --type requer um valor (ex: --type INPUT)");
    }

    String amount = ctx.get("amount");
    if (amount == null || amount.trim().isEmpty() || "true".equalsIgnoreCase(amount)) {
      messages.add("O argumento --amount requer um valor (ex: --amount 10.50)");
    }

    String dateTime = ctx.get("dateTime");
    if (dateTime == null || dateTime.trim().isEmpty() || "true".equalsIgnoreCase(dateTime)) {
      messages.add("O argumento --dateTime requer um valor (ex: --dateTime '2026-04-04 10:00:00')");
    }

    if (ctx.has("description")) {
      String description = ctx.get("description");
      if (description == null || description.trim().isEmpty() || "true".equalsIgnoreCase(description)) {
        messages.add("Se informado, --description deve receber um texto válido (ex: --description \"Compra mercado\")");
      }
    }

    if (ctx.has("installments")) {
      String installments = ctx.get("installments");
      if (installments == null || installments.trim().isEmpty() || "true".equalsIgnoreCase(installments)) {
        messages.add("Se informado, --installments deve receber um número inteiro maior que zero (ex: --installments 1)");
      } else {
        try {
          int parsedInstallments = Integer.parseInt(installments);
          if (parsedInstallments <= 0) {
            messages.add("Se informado, --installments deve ser maior que zero");
          }
        } catch (NumberFormatException exception) {
          messages.add("Se informado, --installments deve ser um número inteiro (ex: 1, 2, 3)");
        }
      }
    }

    String category = ctx.get("category");
    if(category == null || category.trim().isEmpty() || "true".equalsIgnoreCase(category)){
      messages.add("O argumento --category requer um valor (ex: --category [CATEGORIA EXISTENTE])");
    }

    return messages;
  }
}
