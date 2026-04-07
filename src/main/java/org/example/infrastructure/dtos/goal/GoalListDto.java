package org.example.infrastructure.dtos.goal;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record GoalListDto(List<GoalDto> goals) {

    public GoalListDto {
        goals = (goals == null) ? new ArrayList<>() : new ArrayList<>(goals);
    }

    public static GoalListDto empty() {
        return new GoalListDto(List.of());
    }

    public GoalListDto add(GoalDto dto) {
        List<GoalDto> copy = new ArrayList<>(this.goals);
        copy.add(dto);
        return new GoalListDto(copy);
    }

    public GoalListDto replace(GoalDto dto) {
        List<GoalDto> copy = new ArrayList<>(this.goals);
        for (int i = 0; i < copy.size(); i++) {
            if (copy.get(i).id().equals(dto.id())) {
                copy.set(i, dto);
                return new GoalListDto(copy);
            }
        }
        throw new IllegalStateException("Meta não encontrada para update: " + dto.id());
    }

    public GoalListDto removeById(UUID id) {
        List<GoalDto> copy = new ArrayList<>(this.goals);
        copy.removeIf(g -> g.id().equals(id));
        return new GoalListDto(copy);
    }
}