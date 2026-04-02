package org.example.presentation.cli.core.middleware;

import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.middleware.core.MiddlewareBase;
import org.example.presentation.cli.core.records.ResolvedCommandWithCtx;

public class ExecutableMiddleware extends MiddlewareBase {

    @Override
    public void handle(Object ctx) {
        try{
            if(!(ctx instanceof ResolvedCommandWithCtx)){
                next.handle(ctx);
                return;
            }

            CommandContext commandContext = ((ResolvedCommandWithCtx) ctx).ctx();
            Command command = ((ResolvedCommandWithCtx) ctx).command();

            command.specArgs(commandContext);
            command.execute(commandContext);
        }catch (Exception error){
            next.handle(error);
        }
    }
}
