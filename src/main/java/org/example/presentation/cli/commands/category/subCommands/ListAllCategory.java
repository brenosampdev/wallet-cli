package org.example.presentation.cli.commands.category.subCommands;


import java.util.List;

import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;

public class ListAllCategory implements Command {
    // TODO Make listAll subcommand
    public String name() {
        return "listAll";
    }

    public String info() {
        return """
                List all categories:
                Ex: wallet category listAll:
                
                Title: Category 1
                Description: Description of category 1
                
                Title: Category 2
                Description: Description of category 2
                """;
    }

    public void execute(CommandContext context) {
        // TODO alterar para pegar as categorias do banco de dados
        List<String> categories = List.of(
                "Title: Category 1\nDescription: Description of category 1",
                "Title: Category 2\nDescription: Description of category 2"
        );

        if (categories.isEmpty()) {
            System.out.println("No categories found.");
        }

        System.out.println("Categories:\n");
        for (String category : categories) {
            System.out.println(category + "\n");
        }
    }
}
