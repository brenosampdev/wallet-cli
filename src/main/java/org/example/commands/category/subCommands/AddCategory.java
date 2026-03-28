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
                Add a new category:\
                Ex: wallet category add\
                --title="My category"
                --description="My description of category."
                """;
    }

    @Override
    public void execute(CommandContext context) {
        String title = context.get("title");
        String description = context.get("description");
//
//        if (title == null || title.isBlank()) {
//            System.out.println("Error in category creation. The category must contain a title");
//            return;
//        }
//
//        if (description == null || description.isBlank()) {
//            System.out.println(
//                    "Error in category creation.\n" +
//                    "The category must contain a description"
//            );
//            return;
//        }

        System.out.println("Successful category creation!");
        System.out.println("Titulo da categoria: " + title);
        System.out.println("Descricao: " + description);
    }
}
