package br.inatel.model;

public class Feitico {
    private int idFeitico;
    private String nome;
    private String efeito;
    private String nivelDificuldade;

    // Construtores
    public Feitico(String nome, String efeito, String nivelDificuldade) {
        this.nome = nome;
        this.efeito = efeito;
        this.nivelDificuldade = nivelDificuldade;
    }

    // Getters e Setters
    public int getIdFeitico() {
        return idFeitico;
    }

    public void setIdFeitico(int idFeitico) {
        this.idFeitico = idFeitico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }

    public String getNivelDificuldade() {
        return nivelDificuldade;
    }

    public void setNivelDificuldade(String nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }
}