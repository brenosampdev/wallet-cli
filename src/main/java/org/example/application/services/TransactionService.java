package org.example.application.services;

import java.util.List;
import java.math.BigDecimal;
import java.util.UUID;

import org.example.application.dtos.transaction.TransactionCreateDto;
import org.example.application.dtos.transaction.TransactionUpdateDto;
import org.example.application.mappers.TransactionMapper;
import org.example.domain.entities.CategoryEntity;
import org.example.domain.entities.GoalEntity;
import org.example.domain.entities.TransactionEntity;
import org.example.domain.entities.UserEntity;
import org.example.domain.repositories.ICategoryRepository;
import org.example.domain.repositories.IGoalRepository;
import org.example.domain.repositories.ITransactionRepository;
import org.example.domain.repositories.IUserRepository;

public class TransactionService {
  private ITransactionRepository txRepository;
  private IUserRepository userRepository;
  private ICategoryRepository categoryRepository;
  private IGoalRepository goalRepository;

  public TransactionService(
          ITransactionRepository txRepository,
          IUserRepository userRepository,
          ICategoryRepository categoryRepository,
          IGoalRepository goalRepository
  ){
    this.txRepository = txRepository;
    this.userRepository = userRepository;
    this.categoryRepository = categoryRepository;
    this.goalRepository = goalRepository;
  }

  public TransactionEntity create(TransactionCreateDto dto) {
    try {
      UserEntity user = userRepository.getInfo()
        .orElseThrow(() -> new RuntimeException("usuário não definido"));

      CategoryEntity category = categoryRepository.findByName(dto.categoryName())
        .orElseThrow(() -> new RuntimeException("categoria não existe"));

      TransactionEntity tx = TransactionMapper.toEntity(dto, category.getId());

      user.applyTransaction(tx);
      userRepository.insert(user);
      return txRepository.insert(tx);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public TransactionEntity findById(UUID id) {
    try {
      return txRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("transação não encontrada"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<TransactionEntity> findAll() {
    try {
      return txRepository.findAll();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void delete(UUID id) {
    try {
      findById(id);
      txRepository.deleteById(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteByCategory(String categoryName) {
    try {
      CategoryEntity category = categoryRepository.findByName(categoryName)
        .orElseThrow(() -> new RuntimeException("categoria não encontrada"));

      UUID categoryId = category.getId();
      List<TransactionEntity> toDelete = txRepository.findAll().stream()
        .filter(tx -> tx.getCategoryId().equals(categoryId))
        .toList();

      for (TransactionEntity tx : toDelete) {
        txRepository.deleteById(tx.getId());
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public TransactionEntity update(TransactionUpdateDto dto) {
    try {
      TransactionEntity current = findById(dto.id());

      UUID categoryId;
      if (dto.categoryName() != null && !dto.categoryName().isEmpty()) {
        CategoryEntity category = categoryRepository.findByName(dto.categoryName())
          .orElseThrow(() -> new RuntimeException("categoria não encontrada"));
        categoryId = category.getId();
      } else {
        categoryId = current.getCategoryId();
      }

      TransactionEntity updated = new TransactionEntity(
        current.getId(),
        dto.type() != null ? dto.type() : current.getType(),
        dto.amount() != null ? dto.amount() : current.getAmount(),
        dto.dateTime() != null ? dto.dateTime() : current.getDateTime(),
        dto.description() != null && !dto.description().isEmpty() ? dto.description() : current.getDescription(),
        dto.installments() != null ? dto.installments() : current.getInstallments(),
        categoryId
      );

      txRepository.update(updated);
      return updated;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void applyTransactionToGoals(BigDecimal amount) {
    try {
      List<GoalEntity> goals = goalRepository.findAll();

      for (GoalEntity goal : goals) {
        if (goal.isConcluido()) continue;

        double novoValor = goal.getValorAtual() + amount.doubleValue();
        goal.setValorAtual(novoValor);

        if (novoValor >= goal.getValorAlvo()) {
          goal.setConcluido(true);
          System.out.println("Meta concluida: " + goal.getTitulo());
        }

        goalRepository.update(goal);
      }
    } catch (Exception e) {
      throw new RuntimeException("erro ao atualizar metas: " + e.getMessage());
    }
  }
}
