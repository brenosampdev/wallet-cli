package org.example;


import org.example.presentation.cli.commands.category.subCommands.*;
import org.example.presentation.cli.core.CommandGroup;
import org.example.presentation.cli.core.CommandRegistry;
import org.example.presentation.cli.core.CommandResolver;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.middleware.PipelineMiddleware;
import org.example.presentation.cli.core.records.ResolvedCommand;
import org.example.presentation.cli.core.parser.ArgsParser;
import org.example.middleware.HelpMiddleware;
import org.example.middleware.ParserMiddleware;
import org.example.middleware.ResolverMiddleware;
import org.example.presentation.cli.commands.Hello;

import java.util.Arrays;

public class Wallet {
    public static void main(String[] args) {
        CommandRegistry registry = new CommandRegistry();

        CommandGroup category = new CommandGroup("category");
        category.register(new AddCategory());
        category.register(new ListAllCategory());
        category.register(new RemoveCategory());
        category.register(new UpdateCategory());
        category.register(new ListByNameCategory());

        CommandGroup transaction = new CommandGroup("transaction");
        // TODO make crud transaction

        CommandGroup goal = new CommandGroup("goal");
        // TODO make crud goal

        registry.register(category);
        registry.register(transaction);
        registry.register(goal);
        registry.register(new Hello());

        //TODO: definir o helper
        if (args.length == 0 || args[0].trim().isEmpty()) {
            System.out.println("=".repeat(50));
            System.out.println("  WALLET Available Commands");
            System.out.println("=".repeat(50) + "\n");
            registry.getAllCommands().forEach((_name, cmd) -> {
                System.out.println(cmd.info());
                System.out.println("-".repeat(50) + "\n");
            });
            return;
        }

        String commandName = args[0];
        String[] commandsArgs = Arrays.copyOfRange(args, 1, args.length);

        Command command = registry.get(commandName);

        if (command == null) {
            System.out.println("Unknown command: \"" + commandName + "\"\n");
            System.out.println("Available commands:\n");
            registry.getAllCommands().forEach((_name, cmd) ->
                    System.out.println(cmd.info()));
            return;
        }

        PipelineMiddleware pipeline = new PipelineMiddleware();
        CommandResolver resolver = new CommandResolver();
        ArgsParser parser = new ArgsParser();

        pipeline
                .add(new ResolverMiddleware(resolver))
                .add(new HelpMiddleware())
                .add(new ParserMiddleware(parser));

        ResolvedCommand ctx = new ResolvedCommand(command, commandsArgs);
        pipeline.execute(ctx);
    }
}