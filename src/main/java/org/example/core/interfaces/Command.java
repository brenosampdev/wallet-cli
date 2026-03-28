package org.example.core.interfaces;

import org.example.core.CommandContext;

import java.util.Map;

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

    void specArgs(CommandContext context);

    void execute(CommandContext context);
}
