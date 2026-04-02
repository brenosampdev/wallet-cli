package org.example;


import org.example.middleware.ParserMiddleware;
import org.example.middleware.ResolverMiddleware;
import org.example.presentation.cli.commands.Hello;
import org.example.presentation.cli.core.CommandRegistry;
import org.example.presentation.cli.core.CommandResolver;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.middleware.PipelineMiddleware;
import org.example.presentation.cli.core.parser.ArgsParser;
import org.example.presentation.cli.core.records.ResolvedCommand;

import java.util.Arrays;

public class Wallet {
    public static void main(String[] args) {

        //TODO: definir o helper
        if (args.length == 0) {
            return;
        }

        String commandName = args[0];
        String[] commandsArgs = Arrays.copyOfRange(args, 1, args.length);

        CommandRegistry registry = new CommandRegistry();

        PipelineMiddleware pipeline = new PipelineMiddleware();
        CommandResolver resolver = new CommandResolver();
        ArgsParser parser = new ArgsParser();

        registry.register(new Hello());
        Command command = registry.get(commandName);

        if(command == null){
            System.out.println("Unknown command: " + commandName);
            return;
        }

        pipeline
                .add(new ResolverMiddleware(resolver))
                .add(new ParserMiddleware(parser));


        ResolvedCommand ctx = new ResolvedCommand(command, commandsArgs);

        pipeline.execute(ctx);
    }
}