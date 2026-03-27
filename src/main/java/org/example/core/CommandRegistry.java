package org.example.core;

import org.example.core.interfaces.Command;

import java.util.HashMap;
import java.util.Map;

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
