package org.example.core.records;

import org.example.core.CommandContext;
import org.example.core.interfaces.Command;

public record ResolvedCommandWithCtx (Command command, CommandContext ctx) { }
