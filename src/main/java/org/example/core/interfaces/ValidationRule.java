package org.example.core.interfaces;

import java.util.List;

import org.example.core.CommandContext;

public interface ValidationRule{
    List<String> validate(CommandContext ctx);
}