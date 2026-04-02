package org.example.middleware;

import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.middleware.core.MiddlewareBase;
import org.example.presentation.cli.core.parser.ArgsParser;
import org.example.presentation.cli.core.records.ResolvedCommand;
import org.example.presentation.cli.core.records.ResolvedCommandWithCtx;

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
