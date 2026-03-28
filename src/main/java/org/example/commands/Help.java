package org.example.commands;

import org.example.core.interfaces.Command;
import org.example.core.CommandContext;

import java.util.Map;

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
