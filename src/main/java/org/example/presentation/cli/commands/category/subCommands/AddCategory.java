package org.example.presentation.cli.commands.category.subCommands;


import org.example.application.services.CategoryService;
import org.example.application.dtos.category.CategoryCreateDto;
import org.example.domain.entities.CategoryEntity;
import org.example.presentation.cli.commands.category.dtos.CategoryCreateInputDto;
import org.example.presentation.cli.commands.category.mappers.CategoryInputMapper;
import org.example.presentation.cli.commands.category.validation.AddCategoryValidation;
import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.interfaces.ValidationRule;

import java.util.List;

public class AddCategory implements Command {
    private final CategoryService service;

    public AddCategory(CategoryService service) {
        this.service = service;
    }

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

        CategoryCreateInputDto input = new CategoryCreateInputDto(
            context.get("title"),
            context.get("description")
        );

        CategoryCreateDto dto = CategoryInputMapper.toCreateDto(input);
        CategoryEntity created = service.create(dto);
        System.out.println("Successful category creation!");
        System.out.println("Titulo da categoria: " + created.getTitle());
        System.out.println("Descricao: " + created.getDescription());
    }
}