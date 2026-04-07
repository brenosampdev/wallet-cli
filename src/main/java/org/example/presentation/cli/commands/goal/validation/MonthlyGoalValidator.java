package org.example.presentation.cli.commands.goal.validation;

import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.ValidationRule;

import java.util.ArrayList;
import java.util.List;

public class MonthlyGoalValidator implements ValidationRule {
    public static final MonthlyGoalValidator INSTANCE = new MonthlyGoalValidator();

    private MonthlyGoalValidator() {}

    @Override
    public List<String> validate(CommandContext ctx) {
        List<String> errors = new ArrayList<>();

        String titulo = ctx.get("titulo");
        String valorAlvo = ctx.get("valorAlvo");

        if (titulo == null || titulo.trim().isEmpty()) {
            errors.add("--titulo é obrigatório");
        }

        if (valorAlvo == null || valorAlvo.trim().isEmpty()) {
            errors.add("--valorAlvo é obrigatório");
        } else {
            try {
                double valor = Double.parseDouble(valorAlvo);
                if (valor <= 0) {
                    errors.add("--valorAlvo deve ser maior que zero");
                }
            } catch (NumberFormatException e) {
                errors.add("--valorAlvo deve ser um número válido");
            }
        }

        return errors;
    }
}