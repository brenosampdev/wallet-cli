package org.example.infrastructure.repositories;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.example.domain.entities.GoalEntity;
import org.example.domain.repositories.IGoalRepository;
import org.example.infrastructure.JsonStorage;
import org.example.infrastructure.StorageCollection;
import org.example.infrastructure.dtos.goal.GoalDto;
import org.example.infrastructure.dtos.goal.GoalListDto;
import org.example.infrastructure.mappers.GoalMapper;

public class GoalRepository implements IGoalRepository {

    private final JsonStorage storage;
    private static final StorageCollection COLLECTION = StorageCollection.GOALS;

    public GoalRepository(JsonStorage storage) {
        this.storage = storage;
    }

    private GoalListDto readList() throws IOException {
        return storage.read(COLLECTION, GoalListDto.class).orElse(GoalListDto.empty());
    }

    @Override
    public GoalEntity insert(GoalEntity goal) throws IOException {
        GoalDto dto = GoalMapper.toDto(goal);
        GoalListDto updated = readList().add(dto);
        storage.write(COLLECTION, updated);
        return goal;
    }

    @Override
    public List<GoalEntity> findAll() throws IOException {
        return readList()
                .goals()
                .stream()
                .map(GoalMapper::toEntity)
                .toList();
    }

    @Override
    public void deleteById(UUID id) throws IOException {
        GoalListDto updated = readList().removeById(id);
        storage.write(COLLECTION, updated);
    }

    @Override
    public void update(GoalEntity goal) throws IOException {
        GoalDto dto = GoalMapper.toDto(goal);
        GoalListDto updated = readList().replace(dto);
        storage.write(COLLECTION, updated);
    }
}