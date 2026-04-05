package org.example.infrastructure.bootstrap;

import java.util.Optional;

import org.example.domain.entities.UserEntity;
import org.example.domain.repositories.IUserRepository;

public class UserBootstrap {
  private static final UserBootstrap INSTANCE = new UserBootstrap();
  private boolean initialized = false;

  private UserBootstrap(){}

  public static UserBootstrap getInstance(){
    return INSTANCE;
  }

  public synchronized void init(IUserRepository repository){
    if(initialized) return;

    Optional<UserEntity> user = repository.getInfo();

    if(user.isEmpty()){
      UserEntity entity = new UserEntity();
      repository.insert(entity);
    }

    initialized = true;
  }
}
