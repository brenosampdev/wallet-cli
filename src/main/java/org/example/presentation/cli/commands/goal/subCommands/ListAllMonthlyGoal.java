package org.example.presentation.cli.commands.goal.subCommands;

import org.example.application.services.GoalService;
import org.example.domain.entities.GoalEntity;
import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;

import java.util.List;

public class ListAllMonthlyGoal implements Command {
    private final GoalService service;

    public ListAllMonthlyGoal(GoalService service) {
        this.service = service;
    }

    @Override
    public String name() {
        return "listAll";
    }

    @Override
    public String info() {
        return """
            List all goals:
            Ex: wallet goal listAll
            
            Titulo: My goal
            Descricao: My description
            Valor alvo: 1000.0
            Valor atual: 500.0
            Concluido: false
            """;
    }

    @Override
    public void execute(CommandContext context) {
        List<GoalEntity> goals = service.findAll();

        if (goals.isEmpty()) {
            System.out.println("Nenhuma meta encontrada.");
            return;
        }

        System.out.println("Metas:\n");
        for (GoalEntity goal : goals) {
            System.out.println("ID: " + goal.getId());
            System.out.println("Titulo: " + goal.getTitulo());
            System.out.println("Descricao: " + goal.getDescricao());
            System.out.println("Valor alvo: " + goal.getValorAlvo());
            System.out.println("Valor atual: " + goal.getValorAtual());
            System.out.println("Concluido: " + goal.isConcluido());
            System.out.println();
        }
    }
}