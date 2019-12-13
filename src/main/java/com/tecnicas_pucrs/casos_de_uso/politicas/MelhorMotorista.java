package com.tecnicas_pucrs.casos_de_uso.politicas;

import com.tecnicas_pucrs.interfaces.repositorios.RepoMotoristas;
import com.tecnicas_pucrs.entidades.CategoriaVeiculo;
import com.tecnicas_pucrs.entidades.FormaPagamento;
import com.tecnicas_pucrs.entidades.Motorista;

import java.util.List;

public class MelhorMotorista implements PoliticasDeSelecao {

    public Motorista selecionaMotorista(CategoriaVeiculo categoriaVeiculo, double pontuacaoPassageiro, RepoMotoristas repoMotoristas, FormaPagamento formaPagamento) {
        try{
            List<Motorista> listaParcial = repoMotoristas.getMotoristas();
            listaParcial.removeIf(m -> !m.getFormaPagamento().equals(formaPagamento) && m.getVeiculo().cat.equals(categoriaVeiculo));
            Motorista motorista_selecionado = listaParcial.get(0);
            for (Motorista m : listaParcial){
                if(m.getVeiculo().getCat().equals(categoriaVeiculo)){
                    if(motorista_selecionado.getPontuacaoMedia() < m.getPontuacaoMedia()){
                        motorista_selecionado = m;
                    }
                }
            }
            return motorista_selecionado;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
