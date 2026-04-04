package org.example.commands;

import org.example.core.CommandGroup;
import org.example.core.interfaces.Command;
import org.example.core.CommandContext;

import java.util.Map;

public class Hello implements Command {
    private final CommandGroup group;

    public Hello() {
        this.group = new CommandGroup("hello");
        group.register(new Ping());
    }

    @Override
    public String name() {
        return group.name();
    }

    @Override
    public Map<String, Command> subcommands() {
        return group.subcommands();
    }

    @Override
    public String info() {
        return """
            Command: hello
            hello test command.
            Usage: hello --world <value> --name <value>
            """;
    }

    @Override
    public void execute(CommandContext context) {
        System.out.println("hello world" + "-" + "args: " + context.get("world"));
        System.out.println(context.get("name"));
        System.out.println(context.get("description"));
    }
}

