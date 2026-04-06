package org.example.presentation.cli.core.records;

import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;

public record ResolvedCommandWithCtx (Command command, CommandContext ctx) { }
