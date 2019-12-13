package com.tecnicas_pucrs.entidades;

public class VeiculoSimples extends Veiculo {

    public VeiculoSimples(String placa, String marca, String cor){
        this.placa = placa;
        this.marca = marca;
        this.cor = cor;
        this.cat = CategoriaVeiculo.SIMPLES;
    }

    @Override
    public String toString(){
        return this.placa + "," + this.marca + "," + this.cor + "," + "SIMPLES";
    }
}