package com.tecnicas_pucrs.entidades;

public abstract class Veiculo {

    public String placa;
    public String marca;
    public String cor;
    public CategoriaVeiculo cat;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public CategoriaVeiculo getCat() {
        return cat;
    }

    public void setCat(CategoriaVeiculo cat) {
        this.cat = cat;
    }
}