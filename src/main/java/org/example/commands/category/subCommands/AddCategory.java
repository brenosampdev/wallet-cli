package org.example.commands.category.subCommands;

import org.example.core.CommandContext;
import org.example.core.interfaces.Command;

public class AddCategory implements Command {
    @Override
    public String name() {
        return "add";
    }

    @Override
    public String info() {
        return """
            Add a new category.
            Usage: wallet category add --title="My category" --description="My description"
            """;
    }

    @Override
    public void specArgs(CommandContext context) {
        if (context.has("description")) { // --description asdasdas
            String data = context.get("description"); // asdasdas
             if (data == null || data.trim().isEmpty() || "true".equalsIgnoreCase(data)) {
                throw new IllegalArgumentException("O argumento --description requer um valor (ex: --description=\"texto\")");
            }
        }
    }

    @Override
    public void execute(CommandContext context) {
        String title = context.get("title");
        String description = context.get("description");
        System.out.println("Successful category creation!");
        System.out.println("Titulo da categoria: " + title);
        System.out.println("Descricao: " + description);
    }
}
