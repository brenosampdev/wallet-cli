package org.example.presentation.cli.core.interfaces;

import java.util.List;
import org.example.presentation.cli.core.CommandContext;


public interface ValidationRule{
    List<String> validate(CommandContext ctx);
}