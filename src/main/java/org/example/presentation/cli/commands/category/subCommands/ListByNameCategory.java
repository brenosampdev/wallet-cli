package org.example.presentation.cli.commands.category.subCommands;

import java.util.Optional;
import java.util.List;

import org.example.application.services.CategoryService;
import org.example.domain.entities.CategoryEntity;
import org.example.presentation.cli.commands.category.validation.ListByNameCategoryValidation;
import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.interfaces.ValidationRule;

public class ListByNameCategory implements Command {
    private final CategoryService service;

    public ListByNameCategory(CategoryService service) {
        this.service = service;
    }

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

    @Override
    public List<ValidationRule> specArgs(CommandContext context) {
        return List.of(ListByNameCategoryValidation.INSTANCE);
    }

    public void execute(CommandContext context) {
        String name = context.get("name");
        Optional<CategoryEntity> category = service.findByName(name);

        if (category.isEmpty()) {
            throw new RuntimeException("categoria não encontrada");
        }

        System.out.println("Title: " + category.get().getTitle());
        System.out.println("Description: " + category.get().getDescription());
    }
}
