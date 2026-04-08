package org.example.presentation.cli.commands.goal.subCommands;

import org.example.application.services.GoalService;
import org.example.domain.entities.GoalEntity;
import org.example.presentation.cli.commands.goal.validation.UpdateGoalValidation;
import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.interfaces.ValidationRule;

import java.util.List;
import java.util.UUID;

public class UpdateMonthlyGoal implements Command {
    private final GoalService service;

    public UpdateMonthlyGoal(GoalService service) {
        this.service = service;
    }

    @Override
    public String name() {
        return "update";
    }

    @Override
    public String info() {
        return """
            Update goal progress.
            Usage: wallet goal update --id "uuid-da-meta" --valorAtual 500
            """;
    }

    @Override
    public List<ValidationRule> specArgs(CommandContext context) {
        return List.of(UpdateGoalValidation.INSTANCE);
    }

    @Override
    public void execute(CommandContext context) {
        UUID id = UUID.fromString(context.get("id"));
        double valorAtual = Double.parseDouble(context.get("valorAtual"));

        GoalEntity updated = service.atualizarProgresso(id, valorAtual);
        System.out.println("Meta atualizada com sucesso!");
        System.out.println("Titulo: " + updated.getTitulo());
        System.out.println("Valor atual: " + updated.getValorAtual());
        System.out.println("Valor alvo: " + updated.getValorAlvo());
        System.out.println("Concluido: " + updated.isConcluido());
    }
}