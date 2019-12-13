package com.tecnicas_pucrs.casos_de_uso.politicas;

import com.tecnicas_pucrs.interfaces.repositorios.RepoMotoristas;
import com.tecnicas_pucrs.entidades.CategoriaVeiculo;
import com.tecnicas_pucrs.entidades.FormaPagamento;
import com.tecnicas_pucrs.entidades.Motorista;

public interface PoliticasDeSelecao {

    public Motorista selecionaMotorista(CategoriaVeiculo categoriaVeiculo, double pontuacaoPassageiro, RepoMotoristas repoMotoristas, FormaPagamento formaPagamento);

}
