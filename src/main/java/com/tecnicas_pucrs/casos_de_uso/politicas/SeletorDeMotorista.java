package com.tecnicas_pucrs.casos_de_uso.politicas;

import com.tecnicas_pucrs.interfaces.repositorios.RepoMotoristas;
import com.tecnicas_pucrs.entidades.CategoriaVeiculo;
import com.tecnicas_pucrs.entidades.FormaPagamento;
import com.tecnicas_pucrs.entidades.Motorista;


public class SeletorDeMotorista{

    private PoliticasDeSelecao politica;

    public SeletorDeMotorista(PoliticasDeSelecao politica){
        this.politica = politica;
    }

    public void setPolitica(PoliticasDeSelecao politica) {
        this.politica = politica;
    }

    public Motorista selecionaMotoristaParaViagem(CategoriaVeiculo categoriaVeiculo, double pontuacaoPassageiro, RepoMotoristas repoMotoristas, FormaPagamento formaPagamento){

        return politica.selecionaMotorista(categoriaVeiculo, pontuacaoPassageiro, repoMotoristas, formaPagamento);
    }
}