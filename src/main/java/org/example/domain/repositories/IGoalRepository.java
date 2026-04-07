package org.example.domain.repositories;

import org.example.domain.entities.GoalEntity;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IGoalRepository {
    GoalEntity insert(GoalEntity goal) throws IOException;
    void update(GoalEntity goal) throws IOException;
    void deleteById(UUID id) throws IOException;
    List<GoalEntity> findAll() throws IOException;
}