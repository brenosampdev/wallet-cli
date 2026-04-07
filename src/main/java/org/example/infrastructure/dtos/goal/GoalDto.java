package org.example.infrastructure.dtos.goal;

import java.util.UUID;

public record GoalDto(
        UUID id,
        String titulo,
        String descricao,
        double valorAlvo,
        double valorAtual,
        boolean concluido
) {
    public GoalDto {
        id = (id == null) ? UUID.randomUUID() : id;
        titulo = (titulo == null) ? "" : titulo.trim();
        descricao = (descricao == null) ? "" : descricao;
    }
}