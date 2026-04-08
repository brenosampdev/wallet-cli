package org.example.presentation.cli.commands.goal.validation;

import java.util.ArrayList;
import java.util.List;

import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.ValidationRule;

public class RemoveGoalValidation implements ValidationRule {
    public static final RemoveGoalValidation INSTANCE = new RemoveGoalValidation();

    private RemoveGoalValidation() {}

    @Override
    public List<String> validate(CommandContext ctx) {
        List<String> messages = new ArrayList<>();

        String id = ctx.get("id");
        if (id == null || id.trim().isEmpty() || "true".equalsIgnoreCase(id)) {
            messages.add("O argumento --id requer um valor (ex: --id \"uuid-da-meta\")");
        }

        return messages;
    }
}