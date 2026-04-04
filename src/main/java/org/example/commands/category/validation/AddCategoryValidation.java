package org.example.commands.category.validation;

import java.util.ArrayList;
import java.util.List;

import org.example.core.CommandContext;
import org.example.core.interfaces.ValidationRule;

public class AddCategoryValidation implements ValidationRule{
  public static final AddCategoryValidation INSTANCE = new AddCategoryValidation(); 

  private AddCategoryValidation(){}

  @Override
  public List<String> validate(CommandContext ctx) {
        List<String> messages = new ArrayList<>();
        String title = ctx.get("title");
        if (title == null || title.trim().isEmpty() || "true".equalsIgnoreCase(title)) {
            messages.add("O argumento --title requer um valor (ex: --title \"Minha categoria\")");
        }

        String description = ctx.get("description");
        if (description == null || description.trim().isEmpty() || "true".equalsIgnoreCase(description)) {
            messages.add("O argumento --description requer um valor (ex: --description \"texto\")");
        }

    return messages;
  }
}
