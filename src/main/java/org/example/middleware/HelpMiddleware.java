package org.example.middleware;

import org.example.presentation.cli.core.middleware.core.MiddlewareBase;
import org.example.presentation.cli.core.records.ResolvedCommand;

public class HelpMiddleware extends MiddlewareBase {

    @Override
    public void handle(Object ctx) {
        if(!(ctx instanceof ResolvedCommand data)){
            next.handle(ctx);
            return;
        }

        if(!data.command().isLeaf() && data.args().length == 0){
            System.out.println(data.command().info());
            return;
        } else if (!data.command().isLeaf()
                && data.args().length > 0
                && !data.args()[0].startsWith("--")) {
            System.out.println("Unknown subcommand: " + data.args()[0]);
            System.out.println(data.command().info());
            return;
        }

        next.handle(ctx);
    }
}
