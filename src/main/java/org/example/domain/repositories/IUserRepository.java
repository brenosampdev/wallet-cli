package org.example.domain.repositories;

import java.util.Optional;

import org.example.domain.entities.UserEntity;

public interface IUserRepository {
  UserEntity insert(UserEntity user);

  Optional<UserEntity> getInfo();
}
