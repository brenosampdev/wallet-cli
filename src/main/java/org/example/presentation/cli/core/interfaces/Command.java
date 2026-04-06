package org.example.presentation.cli.core.interfaces;

import java.util.List;
import java.util.Map;

import org.example.presentation.cli.core.CommandContext;

public interface Command {
    String name();

    default Map<String, Command> subcommands() {
        return Map.of();
    }

    default Boolean isLeaf() {
        return subcommands().isEmpty();
    }

    default String info(){
        return "no documentation command";
    }

    default List<ValidationRule> specArgs(CommandContext context) {
        return List.of();
    }

    void execute(CommandContext context);
}
