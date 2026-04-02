package org.example.presentation.cli.core.interfaces;

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

    void specArgs(CommandContext context) throws Exception;

    void execute(CommandContext context);
}
