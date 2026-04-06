package org.example.middleware;

import org.example.presentation.cli.core.CommandResolver;
import org.example.presentation.cli.core.middleware.core.MiddlewareBase;
import org.example.presentation.cli.core.records.ResolvedCommand;

public class ResolverMiddleware extends MiddlewareBase {
    private final CommandResolver resolver;
    public ResolverMiddleware(CommandResolver resolver){
        this.resolver = resolver;
    }

    @Override
    public void handle(Object ctx) {
        if(!(ctx instanceof ResolvedCommand data)){
            next.handle(ctx);
            return;
        }

        ResolvedCommand resolved = this.resolver.resolve(data.args(), data.command());

        next.handle(resolved);
    }
}
