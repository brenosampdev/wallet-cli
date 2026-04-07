package org.example.application.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.example.application.dtos.transaction.TransactionCreateDto;
import org.example.domain.entities.CategoryEntity;
import org.example.domain.entities.GoalEntity;
import org.example.domain.entities.TransactionEntity;
import org.example.domain.entities.UserEntity;
import org.example.domain.enums.transactions.TransactionType;
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

  public void insert(TransactionCreateDto dto){
    try {
      Optional<UserEntity> user = userRepository.getInfo();

      if(user.isEmpty()){
        throw new RuntimeException("usuario não definido");
      }

      Optional<CategoryEntity> category = this.categoryRepository.findByName(dto.categoryName());

      if(category.isEmpty()){
        throw new RuntimeException("categoria não existe");
      }

      UUID categoryId = category.get().getId();
      TransactionEntity tx = new TransactionEntity(
              dto.type(),
              dto.amount(),
              dto.dateTime(),
              dto.description(),
              dto.installments(),
              categoryId
      );

      user.get().applyTransaction(tx);
      userRepository.insert(user.get());
      txRepository.insert(tx);

      if (tx.getType() == TransactionType.OUTPUT) {
        applyTransactionToGoals(tx.getAmount());
      }

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