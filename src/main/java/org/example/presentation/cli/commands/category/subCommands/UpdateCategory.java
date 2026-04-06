package org.example.presentation.cli.commands.category.subCommands;

import java.util.List;

import org.example.application.dtos.category.CategoryUpdateDto;
import org.example.application.services.CategoryService;
import org.example.domain.entities.CategoryEntity;
import org.example.presentation.cli.commands.category.dtos.CategoryUpdateInputDto;
import org.example.presentation.cli.commands.category.mappers.CategoryInputMapper;
import org.example.presentation.cli.commands.category.validation.UpdateCategoryValidation;
import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.interfaces.ValidationRule;

public class UpdateCategory implements Command {
    private final CategoryService service;

    public UpdateCategory(CategoryService service) {
        this.service = service;
    }

    public String name() {
        return "update";
    }

    public String info() {
        return """
            Update a category.
            Usage: wallet category update --oldTitle="Category 1" --newTitle="New Title"
            """;
    }

    @Override
    public List<ValidationRule> specArgs(CommandContext context) {
        return List.of(UpdateCategoryValidation.INSTANCE);
    }

    public void execute(CommandContext context) {
        CategoryUpdateInputDto input = new CategoryUpdateInputDto(
            context.get("oldTitle"),
            context.get("newTitle"),
            context.get("description")
        );

        CategoryUpdateDto dto = CategoryInputMapper.toUpdateDto(input);

        CategoryEntity updated = service.update(dto);
        System.out.println("Categoria atualizada com sucesso!");
        System.out.println("Title: " + updated.getTitle());
        System.out.println("Description: " + updated.getDescription());
    }
}
