package com.tecnicas_pucrs.entidades;

public class VeiculosFactory {

    public static Veiculo getVeiculo(String placa, String marca, String cor, CategoriaVeiculo cat) {
        if (cat.equals(CategoriaVeiculo.NORMAL)) {
            return new VeiculoNormal(placa, marca, cor);
        } else if (cat.equals(CategoriaVeiculo.SIMPLES)) {
            return new VeiculoSimples(placa, marca, cor);
        } else {
            return new VeiculoLuxo(placa, marca, cor);
        }
    }
}
