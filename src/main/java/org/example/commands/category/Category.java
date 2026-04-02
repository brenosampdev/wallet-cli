package org.example.commands.category;

import org.example.commands.category.subCommands.AddCategory;
import org.example.commands.category.subCommands.ListAllCategory;
import org.example.core.CommandContext;
import org.example.core.interfaces.Command;

import java.util.Map;

public class Category implements Command {
    private final Map<String, Command> subs;

    public  Category() {
        this.subs = Map.of(
                "add", new AddCategory(),
                "listAll", new ListAllCategory()
        );
    }

    public Map<String, Command> subcommands() {
        return subs;
    }

    public String name() {
        return "category";
    }

    public String info() {
        return """
                Category command
                Subcommands:
                - add: Add a new category
                - rm: Remove a category
                - update: Update a category
                - listByName: List categories by name
                - listAll: List all categories
                """;
    }

    public void execute(CommandContext context) {
        System.out.println(info());
    }
}
