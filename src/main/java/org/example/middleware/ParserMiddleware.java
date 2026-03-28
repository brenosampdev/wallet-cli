package org.example.middleware;

import org.example.core.CommandContext;
import org.example.core.interfaces.Command;
import org.example.core.middleware.core.MiddlewareBase;
import org.example.core.parser.ArgsParser;
import org.example.core.records.ResolvedCommand;
import org.example.core.records.ResolvedCommandWithCtx;

public class ParserMiddleware extends MiddlewareBase {
    private final ArgsParser parser;
    public ParserMiddleware(ArgsParser parser) {
        this.parser = parser;
    }

    @Override
    public void handle(Object ctx) {
        try{
            if(!(ctx instanceof ResolvedCommand data)){
                next.handle(ctx);
                return;
            }

            CommandContext commandContext = parser.parse(data.args());
            ResolvedCommandWithCtx resolvedParser = new ResolvedCommandWithCtx(data.command(), commandContext);
            next.handle(resolvedParser);
        } catch (Exception error) {
            next.handle(error);
        }
    }
}
