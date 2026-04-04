package org.example.core.middleware;

import java.util.ArrayList;
import java.util.List;

import org.example.core.CommandContext;
import org.example.core.interfaces.Command;
import org.example.core.interfaces.ValidationRule;
import org.example.core.middleware.core.MiddlewareBase;
import org.example.core.records.ResolvedCommandWithCtx;

public class ValidationMiddleware extends MiddlewareBase {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String BOLD = "\u001B[1m";
    private static final String YELLOW = "\u001B[33m";

    @Override
    public void handle(Object ctx) {

        if (!(ctx instanceof ResolvedCommandWithCtx resolved)) {
            next.handle(ctx);
            return;
        }

        Command command = resolved.command();
        CommandContext context = resolved.ctx();
        
        List<ValidationRule> listValidations = command.specArgs(context);
        List<String> listErrors = new ArrayList<>();

        for (ValidationRule rule : listValidations){
            List<String> messages = rule.validate(context);
            if(messages.isEmpty()) continue;
            
            for(String msg : messages){
                listErrors.add(msg);
            }
        }

        if(listErrors.isEmpty()){
            next.handle(ctx);
            return;
        }
        
        printErrors(listErrors);
    }

    public static void printErrors(List<String> listErrors) {
        if (listErrors == null || listErrors.isEmpty()) return;

        System.out.println();
        System.out.println(RED + BOLD + "╔══════════════════════════════════════╗" + RESET);
        System.out.println(RED + BOLD + "║            ERROS ENCONTRADOS         ║" + RESET);
        System.out.println(RED + BOLD + "╚══════════════════════════════════════╝" + RESET);

        for (int i = 0; i < listErrors.size(); i++) {
            System.out.println(RED + "  ✖ " + (i + 1) + ") " + listErrors.get(i) + RESET);
        }

        System.out.println(YELLOW + "Total: " + listErrors.size() + " erro(s)." + RESET);
        System.out.println();
    }
}