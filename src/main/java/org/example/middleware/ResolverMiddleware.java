package org.example.middleware;

import org.example.core.CommandResolver;
import org.example.core.interfaces.Command;
import org.example.core.middleware.core.MiddlewareBase;
import org.example.core.records.ResolvedCommand;

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
