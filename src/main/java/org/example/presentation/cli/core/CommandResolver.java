package org.example.presentation.cli.core;

import java.util.Arrays;

import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.records.ResolvedCommand;

public class CommandResolver {

    public ResolvedCommand resolve(String[] args, Command root){
        Command current = root;
        int idx = 0;

        while (idx < args.length){
            Command next = current.subcommands().get(args[idx]);

            if(next == null) break;

            current = next;
            idx++;
        }

        String[] remainingArgs = Arrays.copyOfRange(args, idx, args.length);

        return new ResolvedCommand(current, remainingArgs);
    }
}
