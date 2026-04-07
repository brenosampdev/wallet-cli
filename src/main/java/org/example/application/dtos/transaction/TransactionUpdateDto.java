package org.example.application.dtos.transaction;

import org.example.domain.enums.transactions.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransactionUpdateDto(
    UUID id,
    TransactionType type,
    BigDecimal amount,
    Instant dateTime,
    String description,
    Integer installments,
    String categoryName
) {
    public TransactionUpdateDto {
        if (id == null) throw new IllegalArgumentException("id obrigatório");
        categoryName = (categoryName == null) ? "" : categoryName.trim();
        description = (description == null) ? "" : description;
    }
}
