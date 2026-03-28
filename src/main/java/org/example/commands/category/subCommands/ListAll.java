package org.example.commands.category.subCommands;

import org.example.core.CommandContext;
import org.example.core.interfaces.Command;

public class ListAll implements Command {
    // TODO Make listAll subcommand
    public String name() {
        return "listAll";
    }

    public String info() {
        return """
                List all categories:
                Ex: wallet category listAll:
                --title="Category 1"
                --description="Description of category 1"
                
                --title="Category 1"
                --description="Description of category 1"
                """;
    }

    public void execute(CommandContext context) {
        System.out.println("");
    }
}
