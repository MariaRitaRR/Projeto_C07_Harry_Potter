package br.inatel.model;

public class Casa {
    private int idCasa;
    private String nome;
    private String fundador;
    private String cores;
    private String mascote;
    private String fantasma;

    // Construtores
    public Casa(String nome, String fundador, String cores, String mascote, String fantasma) {
        this.nome = nome;
        this.fundador = fundador;
        this.cores = cores;
        this.mascote = mascote;
        this.fantasma = fantasma;
    }

    // Getters e Setters
    public int getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(int idCasa) {
        this.idCasa = idCasa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFundador() {
        return fundador;
    }

    public void setFundador(String fundador) {
        this.fundador = fundador;
    }

    public String getCores() {
        return cores;
    }

    public void setCores(String cores) {
        this.cores = cores;
    }

    public String getMascote() {
        return mascote;
    }

    public void setMascote(String mascote) {
        this.mascote = mascote;
    }

    public String getFantasma() {
        return fantasma;
    }

    public void setFantasma(String fantasma) {
        this.fantasma = fantasma;
    }
}