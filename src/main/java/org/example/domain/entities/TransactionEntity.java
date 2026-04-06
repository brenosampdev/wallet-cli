package org.example.domain.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import org.example.domain.enums.transactions.TransactionType;

public class TransactionEntity {
  private final UUID id;
  private final TransactionType type;
  private final BigDecimal amount;
  private final Instant dateTime;
  private final String description;
  private final Integer installments;
  private final UUID categoryId;

  public TransactionEntity(
      UUID id,
      TransactionType type,
      BigDecimal amount,
      Instant dateTime,
      String description,
      Integer installments,
      UUID categoryId
  ) {
    this.id = Objects.requireNonNull(id, "id obrigatório");
    this.type = Objects.requireNonNull(type, "tipo obrigatório");
    this.amount = Objects.requireNonNull(amount, "valor obrigatório");
    this.dateTime = Objects.requireNonNull(dateTime, "data/hora obrigatória");
    this.installments = Objects.requireNonNull(installments, "parcelas obrigatório");
    this.categoryId = Objects.requireNonNull(categoryId, "id da categoria obrigatorio");

    if (amount.signum() <= 0) {
      throw new IllegalArgumentException("valor deve ser > 0");
    }
    if (installments <= 0) {
      throw new IllegalArgumentException("parcelas deve ser > 0");
    }

    this.description = description;
  }

  public TransactionEntity(
    TransactionType type,
      BigDecimal amount,
      Instant dateTime,
      String description,
      Integer installments,
      UUID categoryId
  ){
    this(UUID.randomUUID(), type, amount, dateTime, description, installments, categoryId);
  }

  public UUID getId() { return id; }
  public TransactionType getType() { return type; }
  public BigDecimal getAmount() { return amount; }
  public Instant getDateTime() { return dateTime; }
  public String getDescription() { return description; }
  public Integer getInstallments() { return installments; }
  public UUID getCategoryId() { return categoryId; }
}
