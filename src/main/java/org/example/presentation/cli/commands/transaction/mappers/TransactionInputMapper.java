package org.example.presentation.cli.commands.transaction.mappers;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.example.application.dtos.transaction.TransactionCreateDto;
import org.example.domain.enums.transactions.TransactionType;
import org.example.presentation.cli.commands.transaction.dtos.TransactionInputDto;

public final class TransactionInputMapper {
  private TransactionInputMapper() {}

  public static TransactionCreateDto toCreateDto(TransactionInputDto input) {
    return new TransactionCreateDto(
        parseType(input.type()),
        parseAmount(input.amount()),
        parseDateTime(input.dateTime()),
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
}
