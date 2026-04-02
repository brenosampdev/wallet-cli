package org.example.presentation.cli.errors;

import org.example.shared.errors.CommandException;

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
