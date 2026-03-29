package org.example.core.middleware;

import org.example.core.CommandContext;
import org.example.core.interfaces.Command;
import org.example.core.interfaces.Command;
import org.example.core.CommandContext;
import org.example.core.middleware.core.MiddlewareBase;
import org.example.core.records.ResolvedCommandWithCtx;

import java.util.List;

public class ValidationMiddleware extends MiddlewareBase {

    @Override
    public void handle(Object ctx) {

        if (!(ctx instanceof ResolvedCommandWithCtx resolved)) {
            next.handle(ctx);
            return;
        }

        Command command = resolved.command();
        CommandContext context = resolved.ctx();

        List<String> errors = command.validate(context);

        if (!errors.isEmpty()) {
            errors.forEach(System.out::println);
            return; // PARA execução
        }

        next.handle(ctx);
    }
}