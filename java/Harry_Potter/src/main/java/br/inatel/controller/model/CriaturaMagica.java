package br.inatel.controller.model;

public class CriaturaMagica {
    private int idCriatura;
    private String nomeCriatura;
    private String periculosidade;
    private String habitat;

    //construtor
    public CriaturaMagica(int idCriatura, String nomeCriatura, String periculosidade, String habitat) {
        this.idCriatura = idCriatura;
        this.nomeCriatura = nomeCriatura;
        this.periculosidade = periculosidade;
        this.habitat = habitat;

    }

    //getters e setters

    public int getIdCriatura() {
        return idCriatura;
    }

    public void setIdCriatura(int idCriatura) {
        this.idCriatura = idCriatura;
    }

    public String getNomeCriatura() {
        return nomeCriatura;
    }

    public void setNomeCriatura(String nomeCriatura) {
        this.nomeCriatura = nomeCriatura;
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


