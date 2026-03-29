package org.example.commands.category.subCommands;

import org.example.commands.category.validation.CategoryValidator;
import org.example.core.CommandContext;
import org.example.core.interfaces.Command;

import java.util.List;

public class AddCategory implements Command {

    private final CategoryValidator validator = new CategoryValidator();

    @Override
    public String name() {
        return "add";
    }

    @Override
    public String info() {
        return "Add a new category";
    }

    @Override
    public void execute(CommandContext context) {

        String title = context.get("title");
        String description = context.get("description");

        List<String> errors = validator.validate(title, description);

        if (!errors.isEmpty()) {
            errors.forEach(System.out::println);
            return;
        }

        System.out.println("Categoria criada com sucesso!");
    }

    @Override
    public void specArgs(CommandContext context) {
        // pode deixar vazio por enquanto
    }
}