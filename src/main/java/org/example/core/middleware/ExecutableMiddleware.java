package org.example.core.middleware;

import org.example.core.CommandContext;
import org.example.core.interfaces.Command;
import org.example.core.middleware.core.MiddlewareBase;
import org.example.core.records.ResolvedCommandWithCtx;

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
