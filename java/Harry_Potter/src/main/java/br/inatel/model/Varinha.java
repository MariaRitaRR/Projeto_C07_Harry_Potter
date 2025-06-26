package br.inatel.model;

public class Varinha {
    private int idVarinha;
    private String nucleo;
    private String madeira;
    private float comprimento;
    private String flexibilidade;

    // Construtor para criar nova varinha (sem ID)
    public Varinha(String nucleo, String madeira, float comprimento, String flexibilidade) {
        this.nucleo = nucleo;
        this.madeira = madeira;
        this.comprimento = comprimento;
        this.flexibilidade = flexibilidade;
    }

    // Construtor completo (com ID)
    public Varinha(int idVarinha, String nucleo, String madeira, float comprimento, String flexibilidade) {
        this.idVarinha = idVarinha;
        this.nucleo = nucleo;
        this.madeira = madeira;
        this.comprimento = comprimento;
        this.flexibilidade = flexibilidade;
    }

    // Getters e Setters
    public int getIdVarinha() {
        return idVarinha;
    }


    public String getNucleo() {
        return nucleo;
    }

    public void setNucleo(String nucleo) {
        this.nucleo = nucleo;
    }

    public String getMadeira() {
        return madeira;
    }

    public void setMadeira(String madeira) {
        this.madeira = madeira;
    }

    public float getComprimento() {
        return comprimento;
    }

    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }

    public String getFlexibilidade() {
        return flexibilidade;
    }

    public void setFlexibilidade(String flexibilidade) {
        this.flexibilidade = flexibilidade;
    }
}