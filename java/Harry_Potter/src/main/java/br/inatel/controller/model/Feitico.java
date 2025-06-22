package br.inatel.controller.model;

public class Feitico {

    private int idFeitico;
    private String nomeFeitico;
    private String efeito;
    private String nivelDificuldade;

    //construtor
    public Feitico(int idFeitico, String nomeFeitico, String efeito, String nivelDificuldade) {
        this.idFeitico = idFeitico;
        this.nomeFeitico = nomeFeitico;
        this.efeito = efeito;
        this.nivelDificuldade = nivelDificuldade;

    }
    //getters e setters
    public int getIdFeitico() {
        return idFeitico;
    }

    public void setIdFeitico(int idFeitico) {
        this.idFeitico = idFeitico;
    }

    public String getNomeFeitico() {
        return nomeFeitico;
    }

    public void setNomeFeitico(String nomeFeitico) {
        this.nomeFeitico = nomeFeitico;
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
