package org.example.presentation.cli.core;

import java.util.HashMap;
import java.util.Map;

import org.example.presentation.cli.core.interfaces.Command;

public class CommandRegistry {
    private final Map<String , Command> commands = new HashMap<>();


    public Map<String, Command> getAllCommands() {
        return this.commands;
    }

    public void register(Command command) {
       commands.put(command.name(), command);
    }

    public Command get(String name){
        return commands.get(name);
    }
}
