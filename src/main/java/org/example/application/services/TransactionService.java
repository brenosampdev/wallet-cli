package org.example.application.services;

import java.util.Optional;

import org.example.application.dtos.transaction.TransactionCreateDto;
import org.example.application.mappers.TransactionMapper;
import org.example.domain.entities.TransactionEntity;
import org.example.domain.entities.UserEntity;
import org.example.domain.repositories.ITransactionRepository;
import org.example.domain.repositories.IUserRepository;

public class TransactionService {
  private ITransactionRepository txRepository;
  private IUserRepository userRepository;

  public TransactionService(ITransactionRepository txRepository, IUserRepository userRepository){
    this.txRepository = txRepository;
    this.userRepository = userRepository;
  }

  public void insert(TransactionCreateDto dto){
    try {
      Optional<UserEntity> user = userRepository.getInfo();

      if(user.isEmpty()){
        throw new RuntimeException("usuario não definido");
      }

      TransactionEntity tx = TransactionMapper.toEntity(dto);
      user.get().applyTransaction(tx);

      userRepository.insert(user.get());
      txRepository.insert(tx);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
