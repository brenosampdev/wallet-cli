package org.example.commands.goal;

import org.example.core.CommandContext;
import org.example.core.interfaces.Command;

public class Goal implements Command {
    @Override
    public String name() {
        return "goal";
    }

    @Override
    public String info() {
        return """
                Goal command, used with subcommands:
                - add: add a new goal
                - rm: remove a goal
                - update: update a goal
                - listByName: list goals by name
                - listAll: list all goals
                """;
    }

    public void execute(CommandContext context) {
        System.out.println(info());
    }
}
