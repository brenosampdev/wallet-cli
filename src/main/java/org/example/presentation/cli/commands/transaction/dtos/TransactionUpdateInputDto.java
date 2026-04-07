package org.example.presentation.cli.commands.transaction.dtos;

public record TransactionUpdateInputDto(
    String id,
    String type,
    String amount,
    String dateTime,
    String description,
    String installments,
    String categoryName
) {
}
