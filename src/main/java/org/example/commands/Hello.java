package org.example.commands;

import org.example.core.interfaces.Command;
import org.example.core.CommandContext;

import java.util.Map;

public class Hello implements Command {
    private final Map<String, Command> subs;

    public Hello() {
        this.subs = Map.of(
                "ping", new Ping()
        );
    }

    @Override
    public String name() {
        return "hello";
    }

    @Override
    public Map<String, Command> subcommands() {
        return subs;
    }

    @Override
    public String info() {
        return "hello test command\n args:\n hello --world='teste'";
    }

    @Override
    public void execute(CommandContext context) {
        System.out.println("hello world" + "-" + "args: " + context.get("world"));
    }
}

