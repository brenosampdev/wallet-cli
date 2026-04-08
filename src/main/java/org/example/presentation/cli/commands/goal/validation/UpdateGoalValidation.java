package org.example.presentation.cli.commands.goal.validation;

import java.util.ArrayList;
import java.util.List;

import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.ValidationRule;

public class UpdateGoalValidation implements ValidationRule {
    public static final UpdateGoalValidation INSTANCE = new UpdateGoalValidation();

    private UpdateGoalValidation() {}

    @Override
    public List<String> validate(CommandContext ctx) {
        List<String> messages = new ArrayList<>();

        String id = ctx.get("id");
        if (id == null || id.trim().isEmpty() || "true".equalsIgnoreCase(id)) {
            messages.add("O argumento --id requer um valor (ex: --id \"uuid-da-meta\")");
        }

        String valorAtual = ctx.get("valorAtual");
        if (valorAtual == null || valorAtual.trim().isEmpty() || "true".equalsIgnoreCase(valorAtual)) {
            messages.add("O argumento --valorAtual requer um valor (ex: --valorAtual 500)");
        } else {
            try {
                double valor = Double.parseDouble(valorAtual);
                if (valor < 0) {
                    messages.add("--valorAtual nao pode ser negativo");
                }
            } catch (NumberFormatException e) {
                messages.add("--valorAtual deve ser um numero valido");
            }
        }

        return messages;
    }
}