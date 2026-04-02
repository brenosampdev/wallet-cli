package org.example.presentation.cli.commands;

import java.util.Map;

import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;

public class Help implements Command {
    Map<String, Command> commands;

    public Help(Map<String, Command> commands){
        this.commands = commands;
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public void specArgs(CommandContext context) {

    }

    @Override
    public void execute(CommandContext context) {
        StringBuilder helpText = new StringBuilder();

        this.commands.forEach((key, command) -> {
            helpText
                    .append("Command: ")
                    .append(command.name())
                    .append("\n")
                    .append(command.info());
        });

        System.out.println(helpText);
    }
}
