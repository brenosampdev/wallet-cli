package org.example.core.records;

import org.example.core.interfaces.Command;

public record ResolvedCommand(Command command, String[] args) { }
