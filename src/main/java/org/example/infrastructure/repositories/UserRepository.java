package org.example.infrastructure.repositories;

import java.util.Optional;

import org.example.domain.entities.UserEntity;
import org.example.domain.repositories.IUserRepository;
import org.example.infrastructure.JsonStorage;
import org.example.infrastructure.StorageCollection;
import org.example.infrastructure.dtos.user.UserDto;
import org.example.infrastructure.mappers.UserMapper;

public class UserRepository implements IUserRepository{
  private JsonStorage storage;
  private static final StorageCollection COLLECTION = StorageCollection.USER;

  public UserRepository(JsonStorage storage){
    this.storage = storage;
  }

  @Override
  public UserEntity insert(UserEntity user){
    try {
      UserDto dto = UserMapper.toDto(user);
      this.storage.write(COLLECTION, dto);
      return user;
    } catch (Exception e) {
      throw new RuntimeException("Failed to insert user", e);
    } 
  }

  @Override
  public Optional<UserEntity> getInfo() {
    try{
      Optional<UserDto> user = this.storage.read(COLLECTION, UserDto.class);
      return user.map(UserMapper::toEntity);
      
    } catch (Exception e) {
      throw new RuntimeException("Failed to get user", e);
    }
  }
}
