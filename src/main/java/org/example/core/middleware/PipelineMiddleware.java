package org.example.core.middleware;

import org.example.core.middleware.core.MiddlewareBase;

public class PipelineMiddleware {

    public void execute(){
        MiddlewareBase exception = new ExceptionMiddleware();

        exception.handle();
    }
}
