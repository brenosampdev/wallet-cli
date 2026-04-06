package org.example.presentation.cli.core.records;

import org.example.presentation.cli.core.interfaces.Command;

public record ResolvedCommand(Command command, String[] args) { }
