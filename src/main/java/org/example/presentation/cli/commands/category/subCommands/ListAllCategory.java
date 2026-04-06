package org.example.presentation.cli.commands.category.subCommands;


import java.util.List;

import org.example.application.services.CategoryService;
import org.example.domain.entities.CategoryEntity;
import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;

public class ListAllCategory implements Command {
    private final CategoryService service;

    public ListAllCategory(CategoryService service) {
        this.service = service;
    }

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
        List<CategoryEntity> categories = service.findAll();

        if (categories.isEmpty()) {
            System.out.println("No categories found.");
            return;
        }

        System.out.println("Categories:\n");
        for (CategoryEntity category : categories) {
            System.out.println("Title: " + category.getTitle());
            System.out.println("Description: " + category.getDescription());
            System.out.println();
        }
    }
}
