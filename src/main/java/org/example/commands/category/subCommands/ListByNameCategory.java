package org.example.commands.category.subCommands;

import org.example.core.CommandContext;
import org.example.core.interfaces.Command;

public class ListByName implements Command {
    // TODO Make listByName subcommand
    public String name() {
        return "listByName";
    }

    public String info() {
        return """
                List categories by name:
                Ex: wallet category listByName --name="Category 1"
                
                Title: Category 1
                Description: Description of category 1
                """;
    }

    public void execute(CommandContext context) {
        System.out.println("");
    }
}
