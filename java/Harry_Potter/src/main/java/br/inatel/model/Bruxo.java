package br.inatel.Model;

public class Bruxo {

    private int idBruxo;
    private String nomeBruxo;
    private int idade;
    private int idCasa;
    private int idVarinha;
    private String dataCadastro;


    // Construtor vazio (útil para algumas operações)
    public Bruxo() {
    }
    // Construtor para criação (sem ID e data)
    public Bruxo(String nomeBruxo, int idade, int idCasa, int idVarinha) {
        this.nomeBruxo = nomeBruxo;
        this.idade = idade;
        this.idCasa = idCasa;
        this.idVarinha = idVarinha;
    }


    //getters e setters
    public int getIdBruxo() {
        return idBruxo;
    }
    // Construtor completo (com todos os campos)
    public Bruxo(int idBruxo, String nomeBruxo, int idade, int idCasa, int idVarinha, String dataCadastro) {
        this.idBruxo = idBruxo;
        this.nomeBruxo = nomeBruxo;
        this.idade = idade;
        this.idCasa = idCasa;
        this.idVarinha = idVarinha;
        this.dataCadastro = dataCadastro;
    }
    //Getter e Setters
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

    public int getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(int idCasa) {
        this.idCasa = idCasa;
    }

    public int getIdVarinha() {
        return idVarinha;
    }

    public void setIdVarinha(int idVarinha) {
        this.idVarinha = idVarinha;
    }


    @Override
    public String toString() {
        return "Bruxo [ID=" + idBruxo +
                ", Nome=" + nomeBruxo +
                ", Idade=" + idade +
                ", Casa(ID)=" + idCasa +
                ", Varinha(ID)=" + idVarinha +
                ", Cadastro=" + dataCadastro + "]";
    }
}