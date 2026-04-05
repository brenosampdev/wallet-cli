package org.example.domain.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class UserEntity {
  private final String name;
  private BigDecimal amount;

  public UserEntity() {
    this(System.getProperty("user.name", "usuario"), BigDecimal.ZERO);
  }

  public UserEntity(String name) {
    this(name, BigDecimal.ZERO);
  }

  public UserEntity(String name, BigDecimal amount) {
    this.name = Objects.requireNonNull(name, "nome obrigatório");
    this.amount = Objects.requireNonNull(amount, "saldo obrigatório");
  }

  public static UserEntity fromPersistence(
      String name,
      BigDecimal amount
  ) {
    return new UserEntity(name, amount);
  }

  public void applyTransaction(TransactionEntity transaction) {
    Objects.requireNonNull(transaction, "transação obrigatória");

    BigDecimal txAmount = Objects.requireNonNull(transaction.getAmount(), "valor da transação obrigatório");
    if (txAmount.signum() <= 0) {
      throw new IllegalArgumentException("valor da transação deve ser > 0");
    }

    switch (transaction.getType()) {
      case INPUT -> this.amount = this.amount.add(txAmount);
      case OUTPUT -> {
        if (this.amount.compareTo(txAmount) < 0) {
          throw new IllegalStateException("saldo insuficiente");
        }
        this.amount = this.amount.subtract(txAmount);
      }
      default -> throw new IllegalArgumentException("tipo de transação inválido");
    }
  }

  public String getName() { return name; }
  public BigDecimal getAmount() { return amount; }
}
