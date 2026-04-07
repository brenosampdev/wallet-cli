package org.example.application.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.example.domain.entities.GoalEntity;
import org.example.domain.repositories.IGoalRepository;

public class GoalService {
    private final IGoalRepository goalRepository;

    public GoalService(IGoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public GoalEntity create(String titulo, String descricao, double valorAlvo) {
        try {
            if (titulo == null || titulo.trim().isEmpty()) {
                throw new RuntimeException("título da meta é obrigatório");
            }
            if (valorAlvo <= 0) {
                throw new RuntimeException("valor alvo deve ser maior que zero");
            }

            GoalEntity goal = new GoalEntity();
            goal.setId(UUID.randomUUID());
            goal.setTitulo(titulo.trim());
            goal.setDescricao(descricao == null ? "" : descricao);
            goal.setValorAlvo(valorAlvo);
            goal.setValorAtual(0);
            goal.setConcluido(false);

            return goalRepository.insert(goal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<GoalEntity> findAll() {
        try {
            return goalRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GoalEntity atualizarProgresso(UUID id, double novoValor) {
        try {
            GoalEntity goal = goalRepository.findAll()
                    .stream()
                    .filter(g -> g.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("meta não encontrada"));

            goal.setValorAtual(novoValor);
            if (novoValor >= goal.getValorAlvo()) {
                goal.setConcluido(true);
            }

            goalRepository.update(goal);
            return goal;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(UUID id) {
        try {
            Optional<GoalEntity> goal = goalRepository.findAll()
                    .stream()
                    .filter(g -> g.getId().equals(id))
                    .findFirst();

            if (goal.isEmpty()) {
                throw new RuntimeException("meta não encontrada");
            }

            goalRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}