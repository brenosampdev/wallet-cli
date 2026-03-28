package org.example;


import org.example.commands.Hello;
import org.example.core.CommandRegistry;
import org.example.core.CommandResolver;
import org.example.core.interfaces.Command;
import org.example.core.middleware.PipelineMiddleware;
import org.example.core.records.ResolvedCommand;
import org.example.core.parser.ArgsParser;
import org.example.middleware.ParserMiddleware;
import org.example.middleware.ResolverMiddleware;

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