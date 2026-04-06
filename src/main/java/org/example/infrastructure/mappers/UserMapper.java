package org.example.infrastructure.mappers;

import java.util.Objects;

import org.example.domain.entities.UserEntity;
import org.example.infrastructure.dtos.user.UserDto;

public final class UserMapper {
  private UserMapper() {
  }

  public static UserEntity toEntity(UserDto dto) {
    Objects.requireNonNull(dto, "user dto obrigatório");
    return UserEntity.fromPersistence(dto.name(), dto.amount());
  }

  public static UserDto toDto(UserEntity entity) {
    Objects.requireNonNull(entity, "user entity obrigatória");
    return new UserDto(entity.getName(), entity.getAmount());
  }
}
