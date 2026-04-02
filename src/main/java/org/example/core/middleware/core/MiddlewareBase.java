package org.example.core.middleware.core;

public abstract class MiddlewareBase implements Middleware {
    protected Middleware next;

    public MiddlewareBase setNext(Middleware next){
        this.next = next;
        return (MiddlewareBase) next;
    }
}
