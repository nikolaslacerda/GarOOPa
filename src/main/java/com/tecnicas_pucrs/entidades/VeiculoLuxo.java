package com.tecnicas_pucrs.entidades;

public class VeiculoLuxo extends Veiculo {

    public VeiculoLuxo(String placa, String marca, String cor){
        this.placa = placa;
        this.marca = marca;
        this.cor = cor;
        this.cat = CategoriaVeiculo.LUXO;
    }

    @Override
    public String toString(){
        return this.placa + "," + this.marca + "," + this.cor + "," + "LUXO";
    }
}