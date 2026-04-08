package org.example.presentation.cli.commands.goal.subCommands;

import org.example.application.services.GoalService;
import org.example.presentation.cli.commands.goal.validation.RemoveGoalValidation;
import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.interfaces.ValidationRule;

import java.util.List;
import java.util.UUID;

public class RemoveMonthlyGoal implements Command {
    private final GoalService service;

    public RemoveMonthlyGoal(GoalService service) {
        this.service = service;
    }

    @Override
    public String name() {
        return "rm";
    }

    @Override
    public String info() {
        return """
            Remove a goal.
            Usage: wallet goal rm --id "uuid-da-meta"
            """;
    }

    @Override
    public List<ValidationRule> specArgs(CommandContext context) {
        return List.of(RemoveGoalValidation.INSTANCE);
    }

    @Override
    public void execute(CommandContext context) {
        UUID id = UUID.fromString(context.get("id"));
        service.delete(id);
        System.out.println("Meta removida com sucesso!");
    }
}