package org.example.core.middleware;

public abstract class MiddlewareBase implements Middleware {
    protected Middleware next;

    public MiddlewareBase setNext(Middleware next){
        this.next = next;
        return (MiddlewareBase) next;
    }
}
