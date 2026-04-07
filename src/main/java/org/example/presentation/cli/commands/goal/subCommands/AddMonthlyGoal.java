package org.example.presentation.cli.commands.goal.subCommands;

import org.example.application.services.GoalService;
import org.example.domain.entities.GoalEntity;
import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.interfaces.ValidationRule;
import org.example.presentation.cli.commands.goal.validation.MonthlyGoalValidator;


import java.util.List;

public class AddMonthlyGoal implements Command {
    private final GoalService service;

    public AddMonthlyGoal(GoalService service) {
        this.service = service;
    }

    @Override
    public String name() {
        return "add";
    }

    @Override
    public String info() {
        return """
            Add a new goal.
            Usage: wallet goal add --titulo "My goal" --descricao "My description" --valorAlvo 1000.0
            """;
    }

    @Override
    public List<ValidationRule> specArgs(CommandContext context) {
        return List.of(MonthlyGoalValidator.INSTANCE);
    }

    @Override
    public void execute(CommandContext context) {
        String titulo = context.get("titulo");
        String descricao = context.get("descricao");
        double valorAlvo = Double.parseDouble(context.get("valorAlvo"));

        GoalEntity created = service.create(titulo, descricao, valorAlvo);
        System.out.println("Meta criada com sucesso!");
        System.out.println("Titulo: " + created.getTitulo());
        System.out.println("Descricao: " + created.getDescricao());
        System.out.println("Valor alvo: " + created.getValorAlvo());
    }
}