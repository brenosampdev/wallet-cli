package org.example.presentation.cli.core;

import java.util.Map;

public class CommandContext {
    private final Map<String, String> args;

    public CommandContext(
            Map<String, String> args
    ) {
        this.args = args;
    }

    public String get(String key){
        return args.get(key);
    }

    public boolean has(String key){
        return args.containsKey(key);
    }
}
