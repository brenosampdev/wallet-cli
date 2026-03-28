package org.example.commands.category;

import org.example.commands.category.subCommands.AddCategory;
import org.example.commands.category.subCommands.ListAll;
import org.example.core.CommandContext;
import org.example.core.interfaces.Command;

import java.util.Map;

public class Category implements Command {
    private final Map<String, Command> subs;

    public  Category() {
        this.subs = Map.of(
                "add", new AddCategory()
        );
    }

    public Map<String, Command> subcommands() {
        return subs;
    }

    public String name() {
        return "category";
    }

    public String info() {
        return "category";
    }

    public void execute(CommandContext context) {
        System.out.println("category" + "-" + "args: " + context.get("category"));
    }
}
