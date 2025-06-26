package br.inatel.Model;

public class CriaturaMagica {
    private int idCriatura;
    private String nome;
    private String periculosidade;
    private String habitat;

    // Construtores
    public CriaturaMagica(String nome, String periculosidade, String habitat) {
        this.nome = nome;
        this.periculosidade = periculosidade;
        this.habitat = habitat;
    }

    // Getters e Setters
    public int getIdCriatura() {
        return idCriatura;
    }

    public void setIdCriatura(int idCriatura) {
        this.idCriatura = idCriatura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPericulosidade() {
        return periculosidade;
    }

    public void setPericulosidade(String periculosidade) {
        this.periculosidade = periculosidade;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
}