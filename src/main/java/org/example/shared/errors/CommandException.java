package org.example.shared.errors;

import org.example.shared.errors.interfaces.AppError;

public abstract class CommandException extends Exception implements AppError {

    public CommandException() {
        super();
    }

    public CommandException(String message) {
        super(message);
    }
}
