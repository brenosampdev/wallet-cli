package org.example.application.services;

import java.util.Optional;
import java.util.UUID;

import org.example.application.dtos.transaction.TransactionCreateDto;
import org.example.domain.entities.CategoryEntity;
import org.example.domain.entities.TransactionEntity;
import org.example.domain.entities.UserEntity;
import org.example.domain.repositories.ICategoryRepository;
import org.example.domain.repositories.ITransactionRepository;
import org.example.domain.repositories.IUserRepository;

public class TransactionService {
  private ITransactionRepository txRepository;
  private IUserRepository userRepository;
  private ICategoryRepository categoryRepository;

  public TransactionService(
    ITransactionRepository txRepository, 
    IUserRepository userRepository, 
    ICategoryRepository categoryRepository
  ){
    this.txRepository = txRepository;
    this.userRepository = userRepository;
    this.categoryRepository = categoryRepository;
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
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
