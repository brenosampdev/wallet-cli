package org.example.presentation.cli.commands;

import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.errors.CommandNotFoundException;

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
        return "hello test command\n args:\n hello --world teste";
    }

    @Override
    public void specArgs(CommandContext context) throws Exception{
        if(context.has("description")){
           String data = context.get("description");
           if (data == null || data.trim().isEmpty() || "true".equalsIgnoreCase(data)) {
                throw new CommandNotFoundException("O argumento --description requer um valor (ex: --description=\"texto\")");
            }
        }
    }

    @Override
    public void execute(CommandContext context) {
        System.out.println("hello world" + "-" + "args: " + context.get("world"));
        System.out.println(context.get("name"));
        System.out.println(context.get("description"));
    }
}

