package org.example.presentation.cli.commands.goal.validation;

import java.util.ArrayList;
import java.util.List;

import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.ValidationRule;

public class ListByNameGoalValidation implements ValidationRule {

    public static final ListByNameGoalValidation INSTANCE = new ListByNameGoalValidation();

    private ListByNameGoalValidation() {}

    @Override
    public List<String> validate(CommandContext ctx) {

        List<String> messages = new ArrayList<>();

        String title = ctx.get("title");

        if (title == null || title.trim().isEmpty() || "true".equalsIgnoreCase(title)) {
            messages.add("O argumento --title requer um valor (ex: --title \"Minha meta\")");
        }

        return messages;
    }
}