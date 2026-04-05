package org.example.presentation.cli.commands.transaction.dtos;

public record TransactionInputDto(
    String type,
    String amount,
    String dateTime,
    String description,
    String installments
) {
}
