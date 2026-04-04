package org.example.commands.category.subCommands;

import org.example.commands.category.validation.AddCategoryValidation;
import org.example.core.CommandContext;
import org.example.core.interfaces.Command;
import org.example.core.interfaces.ValidationRule;

import java.util.List;

public class AddCategory implements Command {
    @Override
    public String name() {
        return "add";
    }

    @Override
    public String info() {
        return """
            Add a new category.
            Usage: wallet category add --title "My category" --description "My description"
            """;
    }

    @Override
    public List<ValidationRule> specArgs(CommandContext context) {
        return List.of(AddCategoryValidation.INSTANCE);           
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