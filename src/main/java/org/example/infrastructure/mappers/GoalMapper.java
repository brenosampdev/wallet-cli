package org.example.infrastructure.mappers;

import org.example.domain.entities.GoalEntity;
import org.example.infrastructure.dtos.goal.GoalDto;

public class GoalMapper {

    public static GoalDto toDto(GoalEntity entity) {
        return new GoalDto(
                entity.getId(),
                entity.getTitulo(),
                entity.getDescricao(),
                entity.getValorAlvo(),
                entity.getValorAtual(),
                entity.isConcluido()
        );
    }

    public static GoalEntity toEntity(GoalDto dto) {
        GoalEntity entity = new GoalEntity();
        entity.setId(dto.id());
        entity.setTitulo(dto.titulo());
        entity.setDescricao(dto.descricao());
        entity.setValorAlvo(dto.valorAlvo());
        entity.setValorAtual(dto.valorAtual());
        entity.setConcluido(dto.concluido());
        return entity;
    }
}