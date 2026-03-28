package org.example;


import org.example.commands.Hello;
import org.example.commands.category.Category;
import org.example.commands.goal.Goal;
import org.example.commands.transaction.Transaction;
import org.example.core.CommandContext;
import org.example.core.CommandRegistry;
import org.example.core.CommandResolver;
import org.example.core.interfaces.Command;
import org.example.core.middleware.ExceptionMiddleware;
import org.example.core.middleware.core.MiddlewareBase;
import org.example.core.records.ResolvedCommand;
import org.example.core.parser.ArgsParser;

import java.util.Arrays;

public class Wallet {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            return;
        }

        String commandName = args[0];
        String[] commandsArgs = Arrays.copyOfRange(args, 1, args.length);

        CommandRegistry registry = new CommandRegistry();
        CommandResolver resolver = new CommandResolver();

        registry.register(new Hello());
        registry.register(new Goal());
        registry.register(new Category());

        Command command = registry.get(commandName);

        if(command == null){
            System.out.println("Unknown command: " + commandName);
            return;
        }

        ResolvedCommand resolved = resolver.resolve(commandsArgs, command);

        ArgsParser parser = new ArgsParser();
        CommandContext context = parser.parse(resolved.args());

        resolved
                .command()
                .execute(context);
    }
}