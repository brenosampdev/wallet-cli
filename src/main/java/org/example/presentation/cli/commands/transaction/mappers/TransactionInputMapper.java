package org.example.presentation.cli.commands.transaction.mappers;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.UUID;

import org.example.application.dtos.transaction.TransactionCreateDto;
import org.example.application.dtos.transaction.TransactionUpdateDto;
import org.example.domain.enums.transactions.TransactionType;
import org.example.presentation.cli.commands.transaction.dtos.TransactionInputDto;
import org.example.presentation.cli.commands.transaction.dtos.TransactionUpdateInputDto;

public final class TransactionInputMapper {
  private TransactionInputMapper() {}

  public static TransactionCreateDto toCreateDto(TransactionInputDto input) {
    return new TransactionCreateDto(
        parseType(input.type()),
        parseAmount(input.amount()),
        input.dateTime() != null ? parseDateTime(input.dateTime()) : null,
        input.description(),
        parseInstallments(input.installments()),
        input.categoryName()
    );
  }

  private static TransactionType parseType(String value) {
    try {
      return TransactionType.valueOf(value.trim().toUpperCase());
    } catch (Exception exception) {
      throw new IllegalArgumentException("--type inválido. Valores aceitos: INPUT, OUTPUT");
    }
  }

  private static BigDecimal parseAmount(String value) {
    try {
      return new BigDecimal(value.trim());
    } catch (Exception exception) {
      throw new IllegalArgumentException("--amount inválido. Exemplo: 10.50");
    }
  }

  private static Instant parseDateTime(String value) {
    try {
      return Instant.parse(value.trim());
    } catch (DateTimeParseException ignored) {
      try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(value.trim(), formatter);
        return localDateTime.toInstant(ZoneOffset.UTC);
      } catch (DateTimeParseException exception) {
        throw new IllegalArgumentException("--dateTime inválido. Use ISO-8601 (2026-04-04T10:00:00Z) ou yyyy-MM-dd HH:mm:ss");
      }
    }
  }

  private static Integer parseInstallments(String value) {
    if (value == null || value.trim().isEmpty()) {
      return 1;
    }
    try {
      int parsed = Integer.parseInt(value.trim());
      if (parsed <= 0) {
        throw new IllegalArgumentException("--installments deve ser maior que zero");
      }
      return parsed;
    } catch (NumberFormatException exception) {
      throw new IllegalArgumentException("--installments inválido. Exemplo: 1");
    }
  }

  private static UUID parseId(String value) {
    try {
      return UUID.fromString(value.trim());
    } catch (Exception exception) {
      throw new IllegalArgumentException("--id inválido. Deve ser um UUID (ex: 550e8400-e29b-41d4-a716-446655440000)");
    }
  }

  public static TransactionUpdateDto toUpdateDto(TransactionUpdateInputDto input) {
    return new TransactionUpdateDto(
        parseId(input.id()),
        input.type() != null ? parseType(input.type()) : null,
        input.amount() != null ? parseAmount(input.amount()) : null,
        input.dateTime() != null ? parseDateTime(input.dateTime()) : null,
        input.description(),
        input.installments() != null ? parseInstallments(input.installments()) : null,
        input.categoryName()
    );
  }
}
