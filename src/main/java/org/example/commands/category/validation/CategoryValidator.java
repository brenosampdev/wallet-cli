package org.example.commands.category.validation;
import org.example.core.validation.NotBlankRule;
import org.example.core.validation.ValidationRule;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public List<String> validate(String title, String description) {

        List<ValidationRule> rules = List.of(
                new NotBlankRule(title, "Category precisa de título"),
                new NotBlankRule(description, "Category precisa de descrição")
        );

        List<String> errors = new ArrayList<>();

        for (ValidationRule rule : rules) {
            String error = rule.validate();
            if (error != null) {
                errors.add(error);
            }
        }

        return errors;
    }
}