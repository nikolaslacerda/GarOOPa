package com.tecnicas_pucrs.entidades;

public class VeiculoNormal extends Veiculo {

    public VeiculoNormal(String placa, String marca, String cor){
        this.placa = placa;
        this.marca = marca;
        this.cor = cor;
        this.cat = CategoriaVeiculo.NORMAL;
    }

    @Override
    public String toString(){
        return this.placa + "," + this.marca + "," + this.cor + "," + "NORMAL";
    }
}