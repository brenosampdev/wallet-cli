package org.example.core.validation;

public class NotBlankRule implements ValidationRule {

    private final String value;
    private final String message;

    public NotBlankRule(String value, String message) {
        this.value = value;
        this.message = message;
    }

    @Override
    public String validate() {
        if (value == null || value.isBlank()) {
            return message;
        }
        return null;

    }
}