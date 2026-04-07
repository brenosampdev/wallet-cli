package org.example.domain.entities;

import java.util.UUID;

public class GoalEntity {

    private UUID id;
    private String titulo;
    private String descricao;
    private double valorAlvo;
    private double valorAtual;
    private boolean concluido;

    public GoalEntity() {}

    public GoalEntity(UUID id, String titulo, String descricao,
                      double valorAlvo, double valorAtual, boolean concluido) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.valorAlvo = valorAlvo;
        this.valorAtual = valorAtual;
        this.concluido = concluido;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValorAlvo() { return valorAlvo; }
    public void setValorAlvo(double valorAlvo) { this.valorAlvo = valorAlvo; }

    public double getValorAtual() { return valorAtual; }
    public void setValorAtual(double valorAtual) { this.valorAtual = valorAtual; }

    public boolean isConcluido() { return concluido; }
    public void setConcluido(boolean concluido) { this.concluido = concluido; }
}