package org.example.errors;

import org.example.errors.interfaces.AppError;

public abstract class CommandException extends Exception implements AppError {

    public CommandException() {
        super();
    }

    public CommandException(String message) {
        super(message);
    }
}
