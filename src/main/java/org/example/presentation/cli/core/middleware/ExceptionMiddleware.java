package org.example.presentation.cli.core.middleware;

import org.example.presentation.cli.core.middleware.core.MiddlewareBase;
import org.example.shared.errors.CommandException;

public class ExceptionMiddleware extends MiddlewareBase {
    @Override
    public void handle(Object ctx) {
        if(ctx instanceof CommandException){
            System.out.printf(((CommandException) ctx).message());
            return;
        }

        System.out.println("unknown error");
    }
}
