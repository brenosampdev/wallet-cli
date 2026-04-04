package org.example.commands;
import org.example.core.CommandContext;
import org.example.core.interfaces.Command;

public class Ping implements Command {

    @Override
    public String name() {
        return "ping";
    }

    @Override
    public String info() {
        return "ping command, used with arg: world ping --world";
    }

    @Override
    public void execute(CommandContext context) {
        System.out.println("ping in world");
    }
}
