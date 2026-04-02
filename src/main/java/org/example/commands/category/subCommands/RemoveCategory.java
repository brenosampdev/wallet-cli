package org.example.commands.category.subCommands;

import org.example.core.CommandContext;
import org.example.core.interfaces.Command;

public class RemoveCategory implements Command {
    // TODO Make remove subcommand
    public String name() {
        return "rm";
    }

    public String info() {
        return """
                Remove a category:
                Ex: wallet category rm --name="Category 1"
                """;
    }

    public void execute(CommandContext context) {
        System.out.println("");
    }
}
