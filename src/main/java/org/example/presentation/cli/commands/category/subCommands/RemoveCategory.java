package org.example.presentation.cli.commands.category.subCommands;

import java.util.List;

import org.example.application.services.CategoryService;
import org.example.presentation.cli.commands.category.validation.RemoveCategoryValidation;
import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.interfaces.ValidationRule;

public class RemoveCategory implements Command {
    private final CategoryService service;

    public RemoveCategory(CategoryService service) {
        this.service = service;
    }

    public String name() {
        return "rm";
    }

    public String info() {
        return """
                Remove a category:
                Ex: wallet category rm --name="Category 1"
                """;
    }

    @Override
    public List<ValidationRule> specArgs(CommandContext context) {
        return List.of(RemoveCategoryValidation.INSTANCE);
    }

    public void execute(CommandContext context) {
        String name = context.get("name");
        service.delete(name);
        System.out.println("Categoria removida com sucesso!");
    }
}
