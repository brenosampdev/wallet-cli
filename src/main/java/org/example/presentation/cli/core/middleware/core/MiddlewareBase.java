package org.example.presentation.cli.core.middleware.core;

public abstract class MiddlewareBase implements Middleware{
    protected Middleware next;

    public MiddlewareBase setNext(Middleware next){
        this.next = next;
        return (MiddlewareBase) next;
    }
}
