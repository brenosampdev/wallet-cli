package org.example.core.middleware;

import org.example.core.middleware.core.MiddlewareBase;

import java.util.*;

public class PipelineMiddleware {
    private final Queue<MiddlewareBase> queue = new LinkedList<>();

    private void setupDefault(){
        queue.add(new ExecutableMiddleware());
        queue.add(new ExceptionMiddleware());
    }

    public PipelineMiddleware add(MiddlewareBase middleware){
        this.queue.add(middleware);
        return this;
    }

    public void execute(Object ctx) {
        if(queue.isEmpty()){
//            throw new Exception("Pipeline is Empty");
        }

        this.setupDefault();

        MiddlewareBase first = queue.remove();
        MiddlewareBase current = first;
        for (MiddlewareBase middlewareBase : this.queue) {
            current.setNext(middlewareBase);
            current = middlewareBase;
        }


        first.handle(ctx);
    }
}
