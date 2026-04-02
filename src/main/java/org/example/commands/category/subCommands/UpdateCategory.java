package org.example.commands.category.subCommands;

import org.example.core.CommandContext;
import org.example.core.interfaces.Command;

public class UpdateCategory implements Command {
    // TODO Make listAll subcommand
    public String name() {
        return "update";
    }

    public String info() {
        return """
            Update a category.
            Usage: wallet category update --name="Category 1" --title="New Title"
            """;
    }

    public void execute(CommandContext context) {
        System.out.println("");
    }
}
