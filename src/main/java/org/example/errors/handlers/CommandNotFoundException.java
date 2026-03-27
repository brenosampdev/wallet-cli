package org.example.errors.handlers;

import org.example.errors.CommandException;

public class CommandNotFoundException extends CommandException {

    public CommandNotFoundException(String message) {
        super(message);
    }

    public String code() {
        return "COMMAND_NOT_FOUND";
    }

    public String message() {
        return getMessage();
    }
}
