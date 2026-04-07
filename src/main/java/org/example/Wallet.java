package org.example;


import org.example.presentation.cli.commands.category.subCommands.*;
import org.example.presentation.cli.commands.transaction.subCommands.*;
import org.example.presentation.cli.commands.goal.subCommands.AddMonthlyGoal;
import org.example.presentation.cli.commands.goal.subCommands.ListAllMonthlyGoal;
import org.example.presentation.cli.commands.transaction.subCommands.AddTransaction;
import org.example.presentation.cli.core.CommandGroup;
import org.example.presentation.cli.core.CommandRegistry;
import org.example.presentation.cli.core.CommandResolver;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.middleware.PipelineMiddleware;
import org.example.presentation.cli.core.records.ResolvedCommand;
import org.example.presentation.cli.core.parser.ArgsParser;
import org.example.application.services.CategoryService;
import org.example.application.services.GoalService;
import org.example.application.services.TransactionService;
import org.example.middleware.HelpMiddleware;
import org.example.middleware.ParserMiddleware;
import org.example.middleware.ResolverMiddleware;
import org.example.presentation.cli.commands.Hello;

import java.util.Arrays;

public class Wallet {

    public static boolean isEmptyCommand(String args[]){
        return args.length == 0 || args[0].trim().isEmpty();
    }

    public static void messageUnknownCommand(String commandName, CommandRegistry registry){
        System.out.println("Unknown command: \"" + commandName + "\"\n");
        messageEmptyCommand(registry);
    }

    public static void messageEmptyCommand(CommandRegistry registry){
        System.out.println("=".repeat(50));
        System.out.println("  WALLET Available Commands");
        System.out.println("=".repeat(50) + "\n");
        registry.getAllCommands().forEach((_name, cmd) -> {
            System.out.println(cmd.info());
            System.out.println("-".repeat(50) + "\n");
        });
    }

    public static void main(String[] args) {
        App app = App.getInstance();
        CommandRegistry registry = new CommandRegistry();

        app.init();
        TransactionService transactionService = app.makeTransactionService();
        CategoryService categoryService = app.makeCategoryService();
        GoalService goalService = app.makeGoalService();

        CommandGroup category = new CommandGroup("category");
        category.register(new AddCategory(categoryService));
        category.register(new ListAllCategory(categoryService));
        category.register(new RemoveCategory(categoryService));
        category.register(new UpdateCategory(categoryService));
        category.register(new ListByNameCategory(categoryService));

        CommandGroup transaction = new CommandGroup("transaction");
        transaction.register(new AddTransaction(transactionService));
        transaction.register(new ListAllTransaction(transactionService));
        transaction.register(new ListByIdTransaction(transactionService));
        transaction.register(new RemoveTransaction(transactionService));
        transaction.register(new UpdateTransaction(transactionService));

        CommandGroup goal = new CommandGroup("goal");
        goal.register(new AddMonthlyGoal(goalService));
        goal.register(new ListAllMonthlyGoal(goalService));

        registry.register(category);
        registry.register(transaction);
        registry.register(goal);
        registry.register(new Hello());

        if(isEmptyCommand(args)){
            messageEmptyCommand(registry);
            return;
        };

        String commandName = args[0];
        String[] commandsArgs = Arrays.copyOfRange(args, 1, args.length);

        Command command = registry.get(commandName);

        if (command == null) {
            messageUnknownCommand(commandName, registry);
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