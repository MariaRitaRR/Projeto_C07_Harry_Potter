package br.inatel.controller.model;

import java.time.LocalDateTime;

public class Bruxo {

    private int idBruxo;
    private String nomeBruxo;
    private int idade;
    private Casa casa;
    private Varinha varinha;
    private LocalDateTime dataCadastro;


    //construtores

    public Bruxo(int idBruxo, String nomeBruxo, int idade, Casa casa, Varinha varinha) {
        this.idBruxo = idBruxo;
        this.nomeBruxo = nomeBruxo;
        this.idade = idade;
        this.casa = casa;
        this.varinha = varinha;
        this.dataCadastro = LocalDateTime.now();
    }
    //getters e setters
    public int getIdBruxo() {
        return idBruxo;
    }

    public void setIdBruxo(int idBruxo) {
        this.idBruxo = idBruxo;
    }

    public String getNomeBruxo() {
        return nomeBruxo;
    }

    public void setNomeBruxo(String nomeBruxo) {
        this.nomeBruxo = nomeBruxo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    public Varinha getVarinha() {
        return varinha;
    }

    public void setVarinha(Varinha varinha) {
        this.varinha = varinha;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
