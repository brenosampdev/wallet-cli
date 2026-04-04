package org.example.presentation.cli.core;


import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.example.presentation.cli.core.interfaces.Command;

public class CommandGroup implements Command {
    private final String name;
    private final Map<String, Command> subCommands = new LinkedHashMap<>();

    public CommandGroup(String name) {
        this.name = name;
    }

    public void register(Command subCommand) {
        subCommands.put(subCommand.name(), subCommand);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Map<String, Command> subcommands() {
        return Collections.unmodifiableMap(subCommands);
    }

    @Override
    public String info() {
        StringBuilder sb = new StringBuilder();
        sb.append("Command: ").append(name()).append("\n");

        if (subCommands.isEmpty()) {
            sb.append("  No subcommands registered.\n");
        } else {
            sb.append("Subcommands:\n\n");
            subCommands.forEach((k, v) -> {
                sb.append("  ").append(k).append("\n");
                for (String line : v.info().split("\n")) {
                    if (!line.trim().isEmpty()) {
                        sb.append("    ").append(line.trim()).append("\n");
                    }
                }
                sb.append("\n");
            });
        }

        return sb.toString();
    }

    @Override
    public void execute(CommandContext context) {
        System.out.println(info());
    }
}
